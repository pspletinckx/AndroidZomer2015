package com.example.fabrice.joetz2.RestService;

import com.example.fabrice.joetz2.Models.LoginToken;
import com.example.fabrice.joetz2.Models.Vacation;

import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.RestAdapter;

/**
 * Created by Fabrice on 8/08/2015.
 */
public class RestClient {

    private static final String BASE_URL = "http://aug2015.devilcrafter.com/";
    private RestService restService;
    private static RestClient singleton;

    public RestClient(){

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        restService = restAdapter.create(RestService.class);
    }

    public static RestClient getInstance(){
        if (singleton==null){
            singleton = new RestClient();
        }
        return singleton;
    }

    public RestService getService(){
        return restService;
    }

    public void getVacations(Callback<List<Vacation>> callback){
        restService.getAllVacations(callback);
    }

    public void getVacation(long vacationId,Callback<Vacation> callback){
        restService.getVacation(vacationId,callback);
    }

    public void login(Map<String, String> paramMap,Callback<LoginToken> callback ){
        restService.login(paramMap, callback);
    }

    public void register(Map<String, String> paramMap, Callback<String> callback){
        restService.register(paramMap, callback);
    }
}
