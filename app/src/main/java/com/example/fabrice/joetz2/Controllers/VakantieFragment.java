package com.example.fabrice.joetz2.Controllers;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fabrice.joetz2.MainActivity;
import com.example.fabrice.joetz2.Models.Inbegrepen;
import com.example.fabrice.joetz2.Models.Vacation;
import com.example.fabrice.joetz2.R;
import com.example.fabrice.joetz2.RestService.NetNico;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.Date;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

//import android.support.v4.app.Fragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class VakantieFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_VAK_ID = "vakantie_id";
    //String imageHost = "https://dl.dropboxusercontent.com/u/33161611/HoGent/joetz/md/";
    private ImageView coverFoto;
    private TextView titelText,promoText,deelnemersText,datumTextView,locatieTextView;
    private TextView meerdereLeeftijdenTextView, wieTextView;
    private TextView basisTextView, enkelTextView, dubbelTextView;
    private TextView inbegrepenTextView, telefoonTextView, emailTextView;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static VakantieFragment newInstance(int sectionNumber, int vakantieId) {
        VakantieFragment fragment = new VakantieFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putInt(ARG_VAK_ID,vakantieId);
        fragment.setArguments(args);
        return fragment;
    }

    public VakantieFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_vakantie_scroll, container, false);
        // Inladen van velden
        coverFoto = (ImageView)rootView.findViewById(R.id.coverImage);
        titelText = (TextView)rootView.findViewById(R.id.titelTextView);
        promoText = (TextView)rootView.findViewById(R.id.promoTextView);
        deelnemersText = (TextView)rootView.findViewById(R.id.deelnemersTextView);
        datumTextView =(TextView) rootView.findViewById(R.id.datumTextView);
        locatieTextView = (TextView) rootView.findViewById(R.id.locatieTextView);

        //meerdereLeeftijdenTextView = (TextView)rootView.findViewById(R.id.meerdereLeeftijdenTextView);
        wieTextView = (TextView)rootView.findViewById(R.id.wieTextView);
        basisTextView = (TextView)rootView.findViewById(R.id.basisTextView);
        enkelTextView = (TextView)rootView.findViewById(R.id.enkelTextView);
        dubbelTextView = (TextView)rootView.findViewById(R.id.dubbelTextView);
        inbegrepenTextView = (TextView)rootView.findViewById(R.id.inbegrepenTextView);
        telefoonTextView =(TextView) rootView.findViewById(R.id.telefoonTextView);
        emailTextView = (TextView) rootView.findViewById(R.id.emailTextView);

        return rootView;
    }

    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));

        Callback<Vacation> callback = new Callback <Vacation>() {

            @Override
            public void success(Vacation vacation, Response response) {
                //update UI here
                Log.d("Statuscode", String.valueOf(response.getStatus()));
                if(vacation.getId()!=0){ //todo backend issue 2
                    //String url = imageHost+vacation.getCoverFoto().getLocatie();
                    Picasso.with(activity).load(vacation.getCoverFoto().getLocatie()).into(coverFoto);
                    titelText.setText(vacation.getTitel());
                    promoText.setText(vacation.getPromotext());
                    deelnemersText.setText(vacation.getAantalDeelnemers()+" Deelnemers");
                    datumTextView.setText("Van "+vacation.getWanneer().getDateBegin()+" tot "+vacation.getWanneer().getDateEnd());//todo date parsing, maybe in gson
                    locatieTextView.setText(vacation.getWaar().getVakantieDomein()+", "+vacation.getWaar().getStad());

                    wieTextView.setText(vacation.getLeeftijd().getMinLeeftijd()+"- tot "+
                                +vacation.getLeeftijd().getMaxLeeftijd()+"-jarigen");
                    basisTextView.setText(vacation.getPrijs().getBasis().toString());
                    enkelTextView.setText(vacation.getPrijs().getSterEnkel().toString());
                    dubbelTextView.setText(vacation.getPrijs().getSterDubbel().toString());

                    inbegrepenTextView.setText("");
                    for (Inbegrepen inc : vacation.getInbegrepen()) {
                        inbegrepenTextView.append(inc.getBasis() + "\n");
                    }
                    telefoonTextView.setText(vacation.getInformatie().getTel());
                    emailTextView.setText(vacation.getInformatie().getEmail());

                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), "Server is niet beschikbaar", Toast.LENGTH_SHORT);
            }
        };

        NetNico.getInstance().getService().getVacation(getArguments().getInt(ARG_VAK_ID), callback);
    }

}
