package com.example.fabrice.joetz2.Controllers;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.fabrice.joetz2.Helpers.RestClient;
import com.example.fabrice.joetz2.Helpers.HelperMethods;
import com.example.fabrice.joetz2.R;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A login screen that offers login via email/password.
 */
public class LoginFragment extends DialogFragment implements Validator.ValidationListener{
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    //private UserLoginTask mAuthTask = null;

    // UI references.
    @Order(1)
    @NotEmpty(messageResId = R.string.email_required)
    @Email(messageResId = R.string.email_invalid)
    private EditText mEmailView;

    @Order(2)
    @NotEmpty(messageResId = R.string.password_required)
    @Password(messageResId = R.string.password_invalid)
    private EditText mPasswordView;

    private Button mEmailSignInButton, mSignUpButton, mSignOutButton;
    private LinearLayout mLogoutForm, mLoginForm;
    private UserLoginTask mAuthTask = null;
    private boolean emailValid;
    private boolean passwordValid;
    private Validator validator;


    /**
     * Initialiseert het scherm de eerste keer dat de activity gestart wordt
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        validator = new Validator(this);
        validator.setValidationListener(this);
        // Set up the login form.

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        Dialog myDialog=getDialog();
        myDialog.setTitle("Inloggen");

        mEmailView = (EditText) v.findViewById(R.id.email_address);
        mPasswordView = (EditText) v.findViewById(R.id.passWord);
        mEmailSignInButton = (Button) v.findViewById(R.id.btnLogIn);
        mSignUpButton = (Button) v.findViewById(R.id.btnSignUp);
        mSignOutButton = (Button) v.findViewById(R.id.btnSingOut);
        mEmailSignInButton.setEnabled(false);
        mLoginForm = (LinearLayout) v.findViewById(R.id.login_form);
        mLogoutForm = (LinearLayout) v.findViewById(R.id.logout_form_buttons);

        setUpListeners();
        //showButtonsForLoggedIn(HelperMethods.isLoggedIn(getApplication()));

        return v;
    }

    /**
     * Initialiseert het scherm als de activity herstart wordt
     */
    @Override
    public void onResume() {
        super.onResume();
        //showButtonsForLoggedIn(HelperMethods.isLoggedIn(getApplication()));
    }

    /**
     * Voegt Listeners toe aan de knoppen en de EditText velden
     * en stelt het gedrag in van de enter knop op het android toetsenbord
     */
    private void setUpListeners() {
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginClicked();
            }
        });
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSignUpClicked();
            }
        });
        /*mSignOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });*/
        /**
         *  Als de inhoud van de tekstvelden ongeldig is doet de enter knop op het toetsenbord bij het laatste
         *  tekstveld niets anders zal de registratie gestart worden
         */
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                boolean isValidKey = keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER;
                boolean isValidAction = actionId == EditorInfo.IME_ACTION_DONE;

                validator.validate();

                if ((isValidAction || isValidKey) && mEmailSignInButton.isEnabled()){
                    onLoginClicked();
                }
                return false;
            }
        });
        /**
         * Valideert de inhoud van het tekstveld wanneer het verandert
         */
        mPasswordView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i2, int i3) {
                validator.validateTill(mPasswordView);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        /**
         * Valideert de inhoud van het tekstveld wanneer het verandert
         */
        mEmailView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i2, int i3) {
                validator.validateTill(mEmailView);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    /**
     * Gaat naar het registreren fragment
     */
    private void onSignUpClicked() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, RegisterFragment.newInstance(6))
                .commit();
    }

    /**
     * Kijkt of het netwerk beschikbaar is en als er internetverbinding is zal de poging om in
     * te loggen starten
     */
    private void onLoginClicked() {
        if (!HelperMethods.isNetworkAvailable(getActivity())) {
            Toast.makeText(getActivity(), "No network connection", Toast.LENGTH_SHORT).show();
        } else {
            attemptLogin();
        }
    }

    /**
     * Doet een login poging met de ingegeven waarden van het formulier
     */
    public void attemptLogin() {

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        mAuthTask = new UserLoginTask(email, password);
        mAuthTask.execute((Void) null);

    }

    @Override
    public void onValidationSucceeded() {
        mEmailSignInButton.setEnabled(true);
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {

        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getActivity());

            // Display error messages ;)
            ((EditText) view).setError(message);
        }
    }

/**
    /**
     * Zal de huidig ingelogde gebruiker uitloggen

    public void signOut(){
        SharedPreferences sharedPref = getApplication()
                .getSharedPreferences(
                        getString(R.string.authorization_preference_file),
                        Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.apply();
        finish();
    }
*/
    /**
     * Als er ingelogd is zal het login_form verdwijnen en het logout_form tevoorschijn komen,
     * als er niet ingelogd is zal het omgekeerde gebeuren
     * @param isLoggedIn true of false afhankelijk van of er ingelogd is
     */
    private void showButtonsForLoggedIn(boolean isLoggedIn) {
        if(isLoggedIn){
            mLogoutForm.setVisibility(View.VISIBLE);
            mLoginForm.setVisibility(View.INVISIBLE);
        }else{
            mLogoutForm.setVisibility(View.INVISIBLE);
            mLoginForm.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Stelt de asynchrone task voor om in te kunnen loggen
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;
        private ProgressDialog progressDialog;
        //private RestClient restClient = new RestClient();

        /**
         * Constructor
         * @param email
         * @param password
         */
        public UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;

        }
        /**
         * Voordat de task gestart wordt zal er een dialog getoond worden

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(LoginActivity.this, getResources().getString(R.string.title_login), getResources().getString(R.string.please_wait), true);
            super.onPreExecute();
        }*/
        /**
         * De parameter map die meegegeven zal worden met het HTTP request naar de server
         * zal hier opgevuld worden en meegegeven worden naar de functie die het
         * request zal versturen
         * @param params
         * @return returnt een boolean, true als het gelukt is, false als het niet gelukt is
         */
        @Override
        protected Boolean doInBackground(Void... params) {

            Map<String, String> loginParameterMap = new HashMap<String, String>();

            //loginParameterMap.put("grant_type", GRANT_TYPE);
            //loginParameterMap.put("username", mEmail);
            //loginParameterMap.put("password", mPassword);
            //loginParameterMap.put("client_id", CLIENT_ID);
            //loginParameterMap.put("client_secret", CLIENT_SECRET);

            return sendLoginRequest(loginParameterMap);

        }
        /**
         *  Zal het http request naar de server versturen en afhankelijk van of het inloggen
         *  wel of niet succesvol was zal het logintoken dat van de server komt
         *  opgeslaan worden in de sharedpreferences
         * @param loginParameterMap de map die de gegevens van de in te loggen gebruiker bevat
         */
        private boolean sendLoginRequest(final Map<String, String> loginParameterMap) {
/*
            LoginToken loginToken;
            try {
                loginToken = restClient.getRestService().login(loginParameterMap);
                if (loginToken != null) {
                    SharedPreferences sharedPref = getApplication()
                            .getSharedPreferences(getString(R.string.authorization_preference_file), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString(getString(R.string.authorization), loginToken.toString());
                    editor.apply();
                    return true;
                }
            } catch (RetrofitError retrofitError) {
                retrofitError.printStackTrace();
                return false;
            }
            */
            return false;
        }
        /**
         * Na de task zal het dialog verdwijnen, de asynchrone task gestopt worden
         * Als het inloggen succesvol is zal naar de parent activity teruggegaan worden
         * anders zal een errorfield ingesteld worden
         * @param success is true als het inloggen succesvol is zoniet false
         */
        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            progressDialog.dismiss();
            if (success) {
                //finish();
            } else {
                mPasswordView.setError(getString(R.string.password_mismatch));
                mPasswordView.requestFocus();
            }
        }
        /**
         * Wanneer er geannuleerd wordt wordt de asynchrone task geannuleerd en
         * zal het dialog verdwijnen
         */
        @Override
        protected void onCancelled() {
            mAuthTask = null;
            progressDialog.dismiss();
        }
    }
}



