package com.example.fabrice.joetz2.RestService;

import com.example.fabrice.joetz2.Models.Vacation;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Fabrice on 29/06/2015.
 */
public class NodePieter {

    private static final String BASE_URL = "http://github-pspletinckx.rhcloud.com/joetz/v1";
    private RestService restService;
    private static NodePieter singleton;

    public NodePieter(){

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        restService = restAdapter.create(RestService.class);
    }
    public static NodePieter getInstance(){
        if (singleton==null){
            singleton = new NodePieter();
        }
        return singleton;
    }
    public RestService getService(){
        return restService;
    }

    public void getVacations(Callback<Vacation> callback){
        restService.getAllVacations(callback);
    }



}
