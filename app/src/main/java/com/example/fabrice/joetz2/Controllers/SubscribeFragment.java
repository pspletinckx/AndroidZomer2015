package com.example.fabrice.joetz2.Controllers;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.fabrice.joetz2.Helpers.SaripaarRules.Rrn;
import com.example.fabrice.joetz2.Models.Gebruiker;
import com.example.fabrice.joetz2.Models.MyselfModel;
import com.example.fabrice.joetz2.Models.SubscribeModel;
import com.example.fabrice.joetz2.R;
import com.example.fabrice.joetz2.RestService.RestClient;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Max;
import com.mobsandgeeks.saripaar.annotation.Min;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;
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
 * {@link SubscribeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SubscribeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubscribeFragment extends Fragment implements Validator.ValidationListener{
    private OnFragmentInteractionListener mListener;
    private Validator validator;
    private SubscribeTask mAuthTask = null;
    private Button mSubscribeButton;

    @Order(1)
    @NotEmpty(messageResId = R.string.naam_betaler_required)
    private EditText mVoornaamBetaler;

    @Order(2)
    @NotEmpty(messageResId = R.string.nnaam_betaler_required)
    private EditText mNaamBetaler;

    @Order(3)
    @NotEmpty(messageResId = R.string.straat_betaler_required)
    private EditText mStraatBetaler;

    @Order(4)
    @NotEmpty(messageResId = R.string.nr_betaler_verplicht)
    private EditText mStraatNrBetaler;

    @Order(5)
    @NotEmpty(messageResId = R.string.gemeente_betaler_required)
    private EditText mGemeenteBetaler;

    @Order(6)
    @NotEmpty(messageResId = R.string.postcode_betaler_verplicht)
    @Max(value = 8999,messageResId = R.string.postcode_invalid)
    @Min(value = 8000,messageResId = R.string.postcode_invalid)
    private EditText mPostcodeBetaler;

    @Order(7)
    @NotEmpty(messageResId = R.string.naam_moeder_required)
    private EditText mNaamMoeder;

    @Order(8)
    @NotEmpty(messageResId = R.string.rrn_moeder_required)
    @Rrn(messageResId = R.string.rrn_invalid)
    private EditText mRrnMoeder;

    @Order(9)
    @NotEmpty(messageResId = R.string.naam_vader_required)
    private EditText mNaamVader;

    @Order(10)
    @NotEmpty(messageResId = R.string.rrn_vader_required)
    @Rrn(messageResId = R.string.rrn_invalid)
    private EditText mRrnVader;

    @Order(11)
    @NotEmpty(messageResId = R.string.rrn_deelnemer_required)
    @Rrn(messageResId = R.string.rrn_invalid)
    private EditText mRrnDeelnemer;

    @Order(12)
    @NotEmpty(messageResId = R.string.voornaam_deelnemer_required)
    private EditText mVoornaamDeelnemer;

    @Order(13)
    @NotEmpty(messageResId = R.string.naam_deelenemer_required)
    private EditText mNaamDeelnemer;

    @Order(14)
    @NotEmpty(messageResId = R.string.straat_deelnemer_required)
    private EditText mStraatDeelenemer;

    @Order(15)
    @NotEmpty(messageResId = R.string.straatnr_deelnemer_required)
    private EditText mStraatNrDeelnemer;

    @Order(16)
    @NotEmpty(messageResId = R.string.gemeente_deelnemer_required)
    private EditText mGemeenteDeelnemer;

    @Order(17)
    @NotEmpty(messageResId = R.string.postcode_deelnemer_required)
    @Max(value = 8999,messageResId = R.string.postcode_invalid)
    @Min(value = 8000,messageResId = R.string.postcode_invalid)
    private EditText mPostCodeDeelnemer;

    @Order(18)
    @NotEmpty(messageResId = R.string.telnr_deelnemer_required)
    @Pattern(regex = "0(\\d{3}|\\d{2})\\d{6}",messageResId = R.string.telnr_invalid)
    private EditText mTelNrDeelnemer;

    @Order(19)
    @NotEmpty(messageResId = R.string.email_deelnemer_required)
    @Email(messageResId = R.string.email_invalid)
    private EditText mEmailDeelnemer;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SubscribeFragment.
     */
    public static SubscribeFragment newInstance(int vakantieId) {
        SubscribeFragment fragment = new SubscribeFragment();
        Bundle args = new Bundle();
        args.putInt("vakantieId", vakantieId);
        fragment.setArguments(args);
        return fragment;
    }

    public SubscribeFragment() {
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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_subscribe, container, false);

        mVoornaamBetaler = (EditText) v.findViewById(R.id.betaler_voornaam);
        mNaamBetaler = (EditText) v.findViewById(R.id.betaler_naam);
        mStraatBetaler = (EditText) v.findViewById(R.id.betaler_straat);
        mStraatNrBetaler = (EditText) v.findViewById(R.id.betaler_straatNr);
        mGemeenteBetaler = (EditText) v.findViewById(R.id.betaler_gemeente);
        mPostcodeBetaler = (EditText) v.findViewById(R.id.betaler_postcode);
        mNaamMoeder = (EditText) v.findViewById(R.id.naam_moeder);
        mRrnMoeder = (EditText) v.findViewById(R.id.rrn_moeder);
        mNaamVader = (EditText) v.findViewById(R.id.naam_vader);
        mRrnVader = (EditText) v.findViewById(R.id.rrn_vader);
        mRrnDeelnemer = (EditText) v.findViewById(R.id.deelnemer_rrn);
        mVoornaamDeelnemer = (EditText) v.findViewById(R.id.deelnemer_voornaam);
        mNaamDeelnemer = (EditText) v.findViewById(R.id.deelnemer_naam);
        mStraatDeelenemer = (EditText) v.findViewById(R.id.deelnemer_straat);
        mStraatNrDeelnemer = (EditText) v.findViewById(R.id.deelnemer_straatNr);
        mGemeenteDeelnemer = (EditText) v.findViewById(R.id.deelnemer_gemeente);
        mPostCodeDeelnemer = (EditText) v.findViewById(R.id.deelnemer_postcode);
        mTelNrDeelnemer = (EditText) v.findViewById(R.id.deelnemer_telNr);
        mEmailDeelnemer = (EditText) v.findViewById(R.id.deelnemer_email);
        mSubscribeButton = (Button) v.findViewById(R.id.inschrijven_button);
        setupListeners();

        return v;
    }

    public void setupListeners(){
        mSubscribeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSubscription();
            }
        });
        mVoornaamBetaler.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validator.validateTill(mVoornaamBetaler);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mNaamBetaler.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validator.validateTill(mNaamBetaler);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mStraatBetaler.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validator.validateTill(mStraatBetaler);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mStraatNrBetaler.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validator.validateTill(mStraatNrBetaler);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mGemeenteBetaler.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validator.validateTill(mGemeenteBetaler);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mPostcodeBetaler.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validator.validateTill(mPostcodeBetaler);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mNaamMoeder.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validator.validateTill(mNaamMoeder);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mRrnMoeder.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validator.validateTill(mRrnMoeder);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mNaamVader.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validator.validateTill(mNaamVader);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mRrnVader.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validator.validateTill(mRrnVader);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mRrnDeelnemer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validator.validateTill(mRrnDeelnemer);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mVoornaamDeelnemer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validator.validateTill(mVoornaamDeelnemer);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mNaamDeelnemer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validator.validateTill(mNaamDeelnemer);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mStraatDeelenemer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validator.validateTill(mStraatDeelenemer);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mStraatNrDeelnemer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validator.validateTill(mStraatNrDeelnemer);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mGemeenteDeelnemer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validator.validateTill(mGemeenteDeelnemer);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mPostCodeDeelnemer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validator.validateTill(mPostCodeDeelnemer);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mTelNrDeelnemer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validator.validateTill(mTelNrDeelnemer);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mEmailDeelnemer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validator.validateTill(mEmailDeelnemer);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onValidationSucceeded() {
        mSubscribeButton.setEnabled(true);
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
    public void attemptSubscription(){
        boolean betaald=false;
        String gemeente=mGemeenteDeelnemer.getText().toString();
        String email=mEmailDeelnemer.getText().toString();
        String adresBetaler=mStraatBetaler.getText().toString() + mStraatNrBetaler.getText().toString() + mPostcodeBetaler.getText().toString() + mGemeenteBetaler.getText().toString();
        String naamBetaler=mVoornaamBetaler.getText().toString() + mNaamBetaler.getText().toString();
        int nr=Integer.parseInt(mStraatNrDeelnemer.getText().toString());
        String naamVader = mNaamVader.getText().toString();
        String naamMoeder=mNaamMoeder.getText().toString();
        int postcode=Integer.parseInt(mPostCodeDeelnemer.getText().toString());
        String rrnDeelnemer=mRrnDeelnemer.getText().toString();
        String rrnVader=mRrnVader.getText().toString();
        String rrnMoeder=mRrnMoeder.getText().toString();
        String straat=mStraatDeelenemer.getText().toString();
        String vacId=String.valueOf(this.getArguments().getInt("vakantieId"));
        String telNr=mTelNrDeelnemer.getText().toString();
        String voornaam=mVoornaamDeelnemer.getText().toString();
        String naam=mNaamDeelnemer.getText().toString();
        String userId="0";

        SubscribeModel model = new SubscribeModel(betaald, gemeente, email, adresBetaler, naamBetaler,nr, naamVader, naamMoeder,postcode,rrnDeelnemer,rrnVader,rrnMoeder,straat,vacId,telNr,voornaam,naam,userId);
        mAuthTask = new SubscribeTask(model);
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
        public void onFragmentInteraction(Uri uri);
    }

    /**
     * De asynchrone task om de inschrijving uit te voeren
     */
    public class SubscribeTask extends AsyncTask<Void, Void, Boolean> {

        private ProgressDialog progressDialog;
        private SubscribeModel model;
        private MyselfModel data2;
        private Gebruiker user;
        private String token;

        public SubscribeTask(SubscribeModel model) {
            this.model = model;
            SharedPreferences sharedPref =
                    getActivity().
                            getSharedPreferences(getString(R.string.authorization_preference_file),
                                    Context.MODE_PRIVATE);
            token = sharedPref.getString(getResources().getString(R.string.authorization), "No token");
        }

        /**
         * Voordat de task gestart wordt zal er een dialog getoond worden
         */
        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(getActivity(), getResources().getString(R.string.title_inschrijven), getResources().getString(R.string.please_wait), true);
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

            Map<String, String> subscrParamMap = new HashMap<String, String>();

            Callback<MyselfModel> callback = new Callback<MyselfModel>() {
                @Override
                public void success(MyselfModel data, Response response) {
                    if(response.getStatus() == 200) {
                        data2 = data;

                        Callback<Gebruiker> gebruikerCallback = new Callback<Gebruiker>() {
                            @Override
                            public void success(Gebruiker data, Response response) {
                                if(response.getStatus() == 200) {
                                    user = data;
                                    model.setUserId(String.valueOf(user.getId()));
                                    sendSubscribeRequest(model);
                                }
                            }
                            @Override
                            public void failure(RetrofitError error) {
                                mAuthTask = null;
                                Log.e("Retroapp", error.getMessage());
                                //Toast.makeText(getActivity(),"Server is niet beschikbaar",Toast.LENGTH_SHORT);
                                progressDialog.dismiss();
                            }
                        };
                        RestClient.getInstance().getAccount(token, data2.getUserName(), gebruikerCallback);
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    mAuthTask = null;
                    Log.e("Retroapp", error.getMessage());
                    progressDialog.dismiss();
                }
            };
            RestClient.getInstance().getMe(token, callback);

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
            //mAuthTask = null;
        }

        /**
         * Zal het http request naar de server versturen en afhankelijk van of het registreren
         * wel of niet succesvol was de succes functie of failure functie aanroepen
         *
         * @param signupParamMap de map die de gegevens van de te registreren gebruiker bevat
         */
        private void sendSubscribeRequest(SubscribeModel subscrmodel) {
            Callback<String> callback = new Callback<String>() {

                @Override
                public void success(String token, Response response) {
                    if(response.getStatus()==200) {
                        progressDialog.dismiss();
                        mAuthTask=null;
                        getFragmentManager().popBackStackImmediate();
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    mAuthTask = null;
                    Log.e("Retroapp", error.getMessage());
                    progressDialog.dismiss();
                }
            };
            RestClient.getInstance().subscribe(token, model, callback);

        }


        /**
         * Wanneer er geannuleerd wordt wordt de asynchrone task geannuleerd en
         * zal het dialog verdwijnen
         */
        @Override
        protected void onCancelled() {
            //mAuthTask = null;
            //progressDialog.dismiss();
        }
    }

}
