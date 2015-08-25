package com.example.fabrice.joetz2.Controllers;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fabrice.joetz2.Helpers.SaripaarRules.Rrn;
import com.example.fabrice.joetz2.MainActivity;
import com.example.fabrice.joetz2.Models.Gebruiker;
import com.example.fabrice.joetz2.R;
import com.example.fabrice.joetz2.RestService.RestClient;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Max;
import com.mobsandgeeks.saripaar.annotation.Min;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Pattern;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegisterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment implements Validator.ValidationListener {

    @Order(1)
    @NotEmpty(messageResId = R.string.rrn_required)
    @Rrn(messageResId = R.string.rrn_invalid)
    private EditText mRrnView;

    @Order(2)
    @NotEmpty(messageResId = R.string.voornaam_required)
    private EditText mVoorNaamView;

    @Order(3)
    @NotEmpty(messageResId = R.string.naam_required)
    private EditText mNaamView;

    @Order(4)
    @NotEmpty(messageResId = R.string.straat_required)
    private EditText mStraatView;

    @Order(5)
    @NotEmpty(messageResId = R.string.straatnr_required)
    private EditText mNrView;

    @Order(6)
    @NotEmpty(messageResId = R.string.gemeente_required)
    private EditText mGemeenteView;

    @Order(7)
    @NotEmpty(messageResId = R.string.postcode_required)
    @Max(value = 8999,messageResId = R.string.postcode_invalid)
    @Min(value = 8000,messageResId = R.string.postcode_invalid)
    private EditText mPostcodeView;

    @Order(8)
    @NotEmpty(messageResId = R.string.telnr_required)
    @Pattern(regex = "0(\\d{3}|\\d{2})\\d{6}",messageResId = R.string.telnr_invalid)
    private EditText mTelNrView;

    @Order(9)
    @NotEmpty(messageResId = R.string.email_required)
    @Email(messageResId = R.string.email_invalid)
    private EditText mEmailView;

    @Order(10)
    @Password(messageResId = R.string.password_required)
    private EditText mPasswordView;

    @Order(11)
    @ConfirmPassword(messageResId = R.string.password_mismatch)
    private EditText mBevestigPasswordView;

    private Validator validator;
    private UserRegisterTask mAuthTask = null;
    private Button mRegistrerenButton;
    private static final String ARG_SECTION_NUMBER = "section_number";
    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RegisterFragment.
     */
    public static RegisterFragment newInstance(int sectionNumber) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Validator.registerAnnotation(Rrn.class);
        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_register, container, false);
        mEmailView = (EditText) v.findViewById(R.id.registreer_email);
        mVoorNaamView = (EditText) v.findViewById(R.id.voornaam);
        mNaamView =(EditText) v.findViewById(R.id.naam);
        mRrnView = (EditText) v.findViewById(R.id.rrn);
        mPasswordView = (EditText) v.findViewById(R.id.registreer_password);
        mBevestigPasswordView = (EditText) v.findViewById(R.id.bevestig_password);
        mTelNrView = (EditText) v.findViewById(R.id.telNr);
        mStraatView = (EditText) v.findViewById(R.id.straat);
        mNrView = (EditText) v.findViewById(R.id.straatNr);
        mGemeenteView = (EditText) v.findViewById(R.id.gemeente);
        mPostcodeView = (EditText) v.findViewById(R.id.postcode);
        mRegistrerenButton = (Button) v.findViewById(R.id.sign_up);

        setUpListeners();

        // Inflate the layout for this fragment
        return v;
    }

    public void setUpListeners(){

        mRegistrerenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegistration();
            }
        });

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

        mVoorNaamView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i2, int i3) {
                validator.validateTill(mVoorNaamView);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mNaamView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i2, int i3) {
                validator.validateTill(mNaamView);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mRrnView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i2, int i3) {
                validator.validateTill(mRrnView);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

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

        mBevestigPasswordView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i2, int i3) {
                validator.validateTill(mBevestigPasswordView);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mTelNrView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i2, int i3) {
                validator.validateTill(mTelNrView);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mStraatView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i2, int i3) {
                validator.validateTill(mStraatView);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mNrView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i2, int i3) {
                validator.validateTill(mNrView);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mGemeenteView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i2, int i3) {
                validator.validateTill(mGemeenteView);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mPostcodeView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i2, int i3) {
                validator.validateTill(mPostcodeView);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onValidationSucceeded() {
        mRegistrerenButton.setEnabled(true);
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
     * Maakt een ouder object aan met de gegevens uit de tekstvelden en geeft dit door
     * naar de asynchrone taak om te registreren
     */
    public void attemptRegistration() {

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String password2 = mBevestigPasswordView.getText().toString();
        String naam = mNaamView.getText().toString();
        String voornaam = mVoorNaamView.getText().toString();
        String rrn = mRrnView.getText().toString();
        String straat = mStraatView.getText().toString();
        String nr = mNrView.getText().toString();
        String gemeente = mGemeenteView.getText().toString();
        String postcode = mPostcodeView.getText().toString();
        String telNr = mTelNrView.getText().toString();

        Gebruiker gebruiker = new Gebruiker(email,password,rrn,voornaam, naam, straat, gemeente, nr, postcode, telNr);
        mAuthTask = new UserRegisterTask(gebruiker, password2);
        mAuthTask.execute((Void) null);

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction();
    }

    /**
     * De asynchrone task om de registratie uit te voeren
     */
    public class UserRegisterTask extends AsyncTask<Void, Void, Boolean> {

        private Gebruiker mGebruiker;
        private final String passwordConfirmed;
        private ProgressDialog progressDialog;

        /**
         * @param gebruiker
         * @param passwordConfirmed
         */
        public UserRegisterTask(Gebruiker gebruiker, String passwordConfirmed) {
            this.mGebruiker = gebruiker;
            this.passwordConfirmed = passwordConfirmed;
        }

        /**
         * Voordat de task gestart wordt zal er een dialog getoond worden
         */
        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(getActivity(), getResources().getString(R.string.title_registreren), getResources().getString(R.string.please_wait), true);
            super.onPreExecute();
        }

        /**
         * De parameter map die meegegeven zal worden met het HTTP request naar de server
         * zal hier opgevuld worden en meegegeven worden naar de functie die het
         * request zal versturen
         *
         * @param voids
         * @return true als het succesvol is zoniet false
         */
        @Override
        protected Boolean doInBackground(Void... voids) {

            Map<String, String> signUpParamMap = new HashMap<String, String>();

            signUpParamMap.put("userName", mGebruiker.getUserName());
            signUpParamMap.put("password", mGebruiker.getPassWord());
            signUpParamMap.put("confirmPassword", passwordConfirmed);
            signUpParamMap.put("PhoneNumber", mGebruiker.getTel());
            signUpParamMap.put("FirstName", mGebruiker.getVoornaam());
            signUpParamMap.put("LastName", mGebruiker.getNaam());
            signUpParamMap.put("RNR", mGebruiker.getRijksregisternummer());
            signUpParamMap.put("City", mGebruiker.getGemeente());
            signUpParamMap.put("PostalCode", mGebruiker.getPostcode());
            signUpParamMap.put("Street", mGebruiker.getStraat());
            signUpParamMap.put("HouseNr", mGebruiker.getNr());
            signUpParamMap.put("Bus", "6");

            sendSignUpRequest(signUpParamMap);
            return true;
        }

        /**
         * Na de task zal het dialog verdwijnen, de asynchrone task gestopt worden
         * Als het registreren succesvol is zal de login activity gestart worden en
         * de huidige activity gestopt worden
         *
         * @param success is true als het registreren succesvol is zoniet false
         */
        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
        }

        /**
         * Zal het http request naar de server versturen en afhankelijk van of het registreren
         * wel of niet succesvol was de succes functie of failure functie aanroepen
         *
         * @param signupParamMap de map die de gegevens van de te registreren gebruiker bevat
         */
        private void sendSignUpRequest(Map<String, String> signupParamMap) {

            Callback<String> gebruiker = new Callback<String>() {
                @Override
                public void success(String gebruiker, Response response) {
                    if(response.getStatus() == 200){
                        getFragmentManager().popBackStackImmediate();
                        Toast.makeText(getActivity(), "Geregistreerd", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        openLoginDialog();
                        mAuthTask = null;
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    progressDialog.dismiss();
                    mAuthTask=null;
                    Toast.makeText(getActivity(), "Niet Geregistreerd", Toast.LENGTH_SHORT).show();
                    mEmailView.setError(getString(R.string.error_existing_email));
                    mEmailView.requestFocus();
                }

            };
            RestClient.getInstance().register(signupParamMap, gebruiker);
        }

        public void openLoginDialog(){
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            Fragment prev = getFragmentManager().findFragmentByTag(getString(R.string.title_login));
            if (prev != null) {
                ft.remove(prev);
            }
            ft.addToBackStack(null);

            // Create and show the dialog.
            LoginFragment newFragment = new LoginFragment();
            newFragment.show(ft, getString(R.string.title_login));
        }

        /**
         * Wanneer er geannuleerd wordt wordt de asynchrone task geannuleerd en
         * zal het dialog verdwijnen
         */
        @Override
        protected void onCancelled() {
            mAuthTask = null;
            //progressDialog.dismiss();
        }
    }


}
