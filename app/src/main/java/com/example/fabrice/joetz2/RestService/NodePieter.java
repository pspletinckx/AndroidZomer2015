package com.example.fabrice.joetz2.RestService;

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

        Callback  callback = new Callback() {

            @Override
            public void success(Object o, Response response) {

            }

            @Override
            public void failure(RetrofitError error) {

            }
        };
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



}
