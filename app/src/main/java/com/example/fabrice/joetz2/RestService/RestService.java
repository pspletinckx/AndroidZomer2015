package com.example.fabrice.joetz2.RestService;

import com.example.fabrice.joetz2.Models.Vacation;
import com.example.fabrice.joetz2.Models.VacationResponse;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Fabrice on 29/06/2015.
 */
public interface RestService {

    @GET("/informatie")
    VacationResponse getAllVacations();

    @GET("/informatie/{vacationId}")
    Vacation getVacation(@Path("vacationId") long vacationId);
}
