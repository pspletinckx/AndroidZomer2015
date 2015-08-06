package com.example.fabrice.joetz2.RestService;

import com.example.fabrice.joetz2.Models.Vacation;
import com.example.fabrice.joetz2.Models.VacationResponse;

import java.util.List;
import retrofit.Callback;

import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Fabrice on 29/06/2015.
 * Deze Interface bepaald hoe we verwachten dat de api eruit ziet
 */
public interface RestService {

    @GET("/vacation")
    void getAllVacations(Callback<List<Vacation>> cb);

    @GET("/vacation/{vacationId}")
    void getVacation(@Path("vacationId") long vacationId,Callback<Vacation>cb);

    //TODO: Methode voor log in
    //TODO: Methode voor registreren
}
