package com.example.fabrice.joetz2.Controllers.Adaptor;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fabrice.joetz2.Models.Vacation;
import com.example.fabrice.joetz2.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Pieter on 2/08/2015.
 */
public class VacationListAdaptor extends ArrayAdapter<Vacation> {

    Context context;
    int resource;
    List<Vacation>data;
    String imageHost = "https://dl.dropboxusercontent.com/u/33161611/HoGent/joetz/md/";

    public VacationListAdaptor(Context context, int resource, List<Vacation> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data=data;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){
        View row = view;
        VakantieTegel tegel = new VakantieTegel();

        if (row==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(resource,parent,false);

            tegel.textTitle = (TextView) row.findViewById(R.id.textTitle);
            tegel.coverImage = (ImageView)row.findViewById(R.id.coverImage);
            row.setTag(tegel);
        }
        else{
            tegel = (VakantieTegel)row.getTag();
        }
        Vacation vacation = data.get(position);
        tegel.textTitle.setText(vacation.getTitel());
        tegel.coverImage.setImageResource(R.drawable.banner_kinderboerderij);//todo maak placeholder
        if(vacation.getCoverFoto()!= null){
            String url = imageHost+vacation.getCoverFoto().getLocatie();
            Picasso.with(context).load(url).into(tegel.coverImage); //todo dit werkt maar niet performant
        }
        return row;
    }

    private class VakantieTegel {
        ImageView coverImage;
        TextView textTitle;
    }
}
