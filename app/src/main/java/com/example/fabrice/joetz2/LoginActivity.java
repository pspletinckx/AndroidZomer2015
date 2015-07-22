package com.example.fabrice.joetz2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fabrice.joetz2.Helpers.HelperMethods;
import com.example.fabrice.joetz2.Helpers.RestClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity {

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    //private UserLoginTask mAuthTask = null;

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private Button mEmailSignInButton, mSignUpButton, mSignOutButton;
    private LinearLayout mLogoutForm, mLoginForm;
    private UserLoginTask mAuthTask = null;
    private boolean emailValid;
    private boolean passwordValid;

    /**
     * Initialiseert het scherm de eerste keer dat de activity gestart wordt
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.email_address);
        mPasswordView = (EditText) findViewById(R.id.passWord);
        mEmailSignInButton = (Button) findViewById(R.id.btnLogIn);
        mSignUpButton = (Button) findViewById(R.id.btnSignUp);
        mSignOutButton = (Button) findViewById(R.id.btnSingOut);
        mEmailSignInButton.setEnabled(false);
        mLoginForm = (LinearLayout) findViewById(R.id.login_form);
        mLogoutForm = (LinearLayout) findViewById(R.id.logout_form_buttons);

        setUpListeners();
        showButtonsForLoggedIn(HelperMethods.isLoggedIn(getApplication()));
    }

    /**
     * Initialiseert het scherm als de activity herstart wordt
     */
    @Override
    protected void onResume() {
        super.onResume();
        showButtonsForLoggedIn(HelperMethods.isLoggedIn(getApplication()));
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

                if ((isValidAction || isValidKey)&& emailValid && passwordValid){
                    attemptLogin();
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
                isPasswordValid(s.toString());
                changeButtonState();
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
                isEmailValid(s.toString());
                changeButtonState();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (emailValid)
                    mEmailView.setError(null);
            }
        });
    }

    /**
     * Gaat naar de registreren activity
     */
    private void onSignUpClicked() {

    }

    /**
     * Kijkt of het netwerk beschikbaar is en als er internetverbinding is zal de poging om in
     * te loggen starten
     */
    private void onLoginClicked() {
        if (!HelperMethods.isNetworkAvailable(getApplicationContext())) {
            Toast.makeText(getBaseContext(), "No network connection", Toast.LENGTH_SHORT).show();
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
    /**
     * Kijkt of de tekst een geldig emailadres is
     * @param email de te valideren string
     */
    private void isEmailValid(String email) {
        String emailRegEx;
        Pattern pattern;
        // Regex for a valid email address
        emailRegEx = "^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,4}$";
        // Compare the regex with the email address
        pattern = Pattern.compile(emailRegEx);
        Matcher matcher = pattern.matcher(email);

        emailValid =  matcher.find();
    }
    /**
     * Kijkt of de tekst overeenkomt met de opgelegde regels voor wachtwoord
     * @param password de te valideren string
     */
    private void isPasswordValid(String password) {
        passwordValid =  !TextUtils.isEmpty(password);
    }

    /**
     * Als alle velden correct gevalideerd zijn zal de knop om in te loggen ge-enabled worden
     * anders zal de knop disabled worden. Bij de ongeldige velden komt ook een errorfield te staan
     */
    private void changeButtonState(){
        if (emailValid && passwordValid){
            mEmailSignInButton.setEnabled(true);
        }else{
            mEmailSignInButton.setEnabled(false);
            if (!emailValid)
                mEmailView.setError(getString(R.string.error_invalid_email));
            if (!passwordValid)
                mPasswordView.setError(getString(R.string.error_field_required));
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
        private RestClient restClient = new RestClient();

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
         */
        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(LoginActivity.this, getResources().getString(R.string.title_login), getResources().getString(R.string.please_wait), true);
            super.onPreExecute();
        }
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
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
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



