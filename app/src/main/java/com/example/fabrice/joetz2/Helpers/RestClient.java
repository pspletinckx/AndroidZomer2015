package com.example.fabrice.joetz2.Helpers;

import com.example.fabrice.joetz2.RestService.RestService;
import retrofit.RestAdapter;

/**
 * Created by Fabrice on 29/06/2015.
 */
public class RestClient {

    private static final String BASE_URL = "http://github-pspletinckx.rhcloud.com/joetz/v1";
    private RestService restService;

    public RestClient(){

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        restService = restAdapter.create(RestService.class);
    }

}
