package com.example.fabrice.joetz2.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Fabrice on 25/08/2015.
 */
public class SubscribeModel {
    @SerializedName("Betaald")
    private boolean betaald;
    @SerializedName("Gemeente")
    private String gemeente;
    @SerializedName("Email")
    private String email;
    @SerializedName("Adres_Betalingspersoon")
    private String adresBetaler;
    @SerializedName("Naam_Betalingspersoon")
    private String naamBetaler;
    @SerializedName("Nr")
    private int nr;
    @SerializedName("Naam_Vader")
    private String naamVader;
    @SerializedName("Naam_Moeder")
    private String naamMoeder;
    @SerializedName("Postcode")
    private int postcode;
    @SerializedName("Rijksregisternummer_Vakantieganger")
    private String rrnDeelnemer;
    @SerializedName("Rijksregisternummer_Vader")
    private String rrnVader;
    @SerializedName("Rijksregisternummer_Moeder")
    private String rrnMoeder;
    @SerializedName("Straat")
    private String straat;
    @SerializedName("SubscriptionForVacationId")
    private String vacId;
    @SerializedName("Tel")
    private String telNr;
    @SerializedName("Voornaam")
    private String voornaam;
    @SerializedName("Naam")
    private String naam;
    @SerializedName("SubscribedUserId")
    private String userId;

    public SubscribeModel(boolean betaald, String gemeente, String email, String adresBetaler, String naamBetaler, int nr, String naamVader, String naamMoeder, int postcode, String rrnDeelnemer, String rrnVader, String rrnMoeder, String straat, String vacId, String telNr, String voornaam, String naam, String userId) {
        this.betaald = betaald;
        this.gemeente = gemeente;
        this.email = email;
        this.adresBetaler = adresBetaler;
        this.naamBetaler = naamBetaler;
        this.nr = nr;
        this.naamVader = naamVader;
        this.naamMoeder = naamMoeder;
        this.postcode = postcode;
        this.rrnDeelnemer = rrnDeelnemer;
        this.rrnVader = rrnVader;
        this.rrnMoeder = rrnMoeder;
        this.straat = straat;
        this.vacId = vacId;
        this.telNr = telNr;
        this.voornaam = voornaam;
        this.naam = naam;
        this.userId = userId;
    }

    public boolean isBetaald() {
        return betaald;
    }

    public void setBetaald(boolean betaald) {
        this.betaald = betaald;
    }

    public String getGemeente() {
        return gemeente;
    }

    public void setGemeente(String gemeente) {
        this.gemeente = gemeente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresBetaler() {
        return adresBetaler;
    }

    public void setAdresBetaler(String adresBetaler) {
        this.adresBetaler = adresBetaler;
    }

    public String getNaamBetaler() {
        return naamBetaler;
    }

    public void setNaamBetaler(String naamBetaler) {
        this.naamBetaler = naamBetaler;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public String getNaamVader() {
        return naamVader;
    }

    public void setNaamVader(String naamVader) {
        this.naamVader = naamVader;
    }

    public String getNaamMoeder() {
        return naamMoeder;
    }

    public void setNaamMoeder(String naamMoeder) {
        this.naamMoeder = naamMoeder;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public String getRrnDeelnemer() {
        return rrnDeelnemer;
    }

    public void setRrnDeelnemer(String rrnDeelnemer) {
        this.rrnDeelnemer = rrnDeelnemer;
    }

    public String getRrnVader() {
        return rrnVader;
    }

    public void setRrnVader(String rrnVader) {
        this.rrnVader = rrnVader;
    }

    public String getRrnMoeder() {
        return rrnMoeder;
    }

    public void setRrnMoeder(String rrnMoeder) {
        this.rrnMoeder = rrnMoeder;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getVacId() {
        return vacId;
    }

    public void setVacId(String vacId) {
        this.vacId = vacId;
    }

    public String getTelNr() {
        return telNr;
    }

    public void setTelNr(String telNr) {
        this.telNr = telNr;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
