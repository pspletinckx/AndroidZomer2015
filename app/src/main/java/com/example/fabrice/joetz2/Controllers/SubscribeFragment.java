package com.example.fabrice.joetz2.Controllers;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.fabrice.joetz2.Models.Gebruiker;
import com.example.fabrice.joetz2.R;
import com.example.fabrice.joetz2.RestService.RestClient;
import com.mobsandgeeks.saripaar.Validator;

import java.util.HashMap;
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
public class SubscribeFragment extends Fragment {
    private LinearLayout mBondLidView, mGegevensBetalerView;

    private OnFragmentInteractionListener mListener;
    private Validator validator;
    //private SubscribeTask mAuthTask = null;



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SubscribeFragment.
     */
    public static SubscribeFragment newInstance() {
        SubscribeFragment fragment = new SubscribeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public SubscribeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_subscribe, container, false);
        mBondLidView = (LinearLayout) v.findViewById(R.id.lid_view);
        mGegevensBetalerView = (LinearLayout) v.findViewById(R.id.betaler);
        return v;
    }


    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.lid_ja:
                if (checked)
                    mBondLidView.setVisibility(View.VISIBLE);
                    break;
            case R.id.lid_nee:
                if (checked)
                    mBondLidView.setVisibility(View.INVISIBLE);
                    break;
            case R.id.contpers_betaler_ja:
                if (checked)
                    mGegevensBetalerView.setVisibility(View.INVISIBLE);
                break;
            case R.id.contpers_betaler_nee:
                if (checked)
                    mGegevensBetalerView.setVisibility(View.VISIBLE);
                break;
        }
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
            //mAuthTask = null;
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
                        //mAuthTask = null;
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    progressDialog.dismiss();
                    //mAuthTask=null;
                    Toast.makeText(getActivity(), "Niet Geregistreerd", Toast.LENGTH_SHORT).show();
                    //mEmailView.setError(getString(R.string.error_existing_email));
                    //mEmailView.requestFocus();
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
            //mAuthTask = null;
            //progressDialog.dismiss();
        }
    }

}
