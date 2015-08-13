package com.example.fabrice.joetz2.RestService;

import com.example.fabrice.joetz2.Models.Vacation;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;

/**
 * Created by Fabrice on 29/06/2015.
 */
public class NetNico {
        //Dit vereist een paar instellingen die moeten gewijzigd worden om deze twee systemen
        //locaal te verbinden
        //http://stackoverflow.com/questions/5433786/configure-iis-express-for-external-access-to-vs2010-project
    private static final String BASE_URL = "http://192.168.56.1:51698";
    private RestService restService;
    private static NetNico singleton;

    public NetNico(){

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        restService = restAdapter.create(RestService.class);
    }
    public static NetNico getInstance(){
        if (singleton==null){
            singleton = new NetNico();
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



}
