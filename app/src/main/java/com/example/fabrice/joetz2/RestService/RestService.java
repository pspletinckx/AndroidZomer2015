package com.example.fabrice.joetz2.RestService;

import com.example.fabrice.joetz2.Models.Gebruiker;
import com.example.fabrice.joetz2.Models.LoginToken;
import com.example.fabrice.joetz2.Models.Vacation;
import com.example.fabrice.joetz2.Models.VacationResponse;

import java.util.List;
import java.util.Map;

import retrofit.Callback;

import retrofit.http.Body;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Fabrice on 29/06/2015.
 * Deze Interface bepaald hoe we verwachten dat de api eruit ziet
 */
public interface RestService {

    @GET("/api/vacation")
    void getAllVacations(Callback<List<Vacation>> cb);

    @GET("/api/vacation/{vacationId}")
    void getVacation(@Path("vacationId") long vacationId,Callback<Vacation>cb);

    @FormUrlEncoded
    @POST("/token")
    void login(@FieldMap Map<String, String> options, Callback<LoginToken> cb);

    @POST("/api/account/register")
    void register(@Body Map<String, String> user, Callback<String> cb);

}
