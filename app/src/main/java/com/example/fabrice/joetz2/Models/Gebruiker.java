package com.example.fabrice.joetz2.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Fabrice on 9/08/2015.
 */
public class Gebruiker {

    @SerializedName("userName")
    private String userName;
    @SerializedName("passWord")
    private String passWord;
    @SerializedName("Rijksregisternummer")
    private String Rijksregisternummer;
    @SerializedName("Voornaam")
    private String Voornaam;
    @SerializedName("Naam")
    private String Naam;
    @SerializedName("Straat")
    private String Straat;
    @SerializedName("Gemeente")
    private String Gemeente;
    @SerializedName("Nr")
    private String Nr;
    @SerializedName("Postcode")
    private String Postcode;
    @SerializedName("Tel")
    private String Tel;

    public Gebruiker(String userName, String passWord, String rijksregisternummer, String voornaam, String naam, String straat, String gemeente, String nr, String postcode, String tel) {
        this.userName = userName;
        this.passWord = passWord;
        Rijksregisternummer = rijksregisternummer;
        Voornaam = voornaam;
        Naam = naam;
        Straat = straat;
        Gemeente = gemeente;
        Nr = nr;
        Postcode = postcode;
        Tel = tel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRijksregisternummer() {
        return Rijksregisternummer;
    }

    public void setRijksregisternummer(String rijksregisternummer) {
        Rijksregisternummer = rijksregisternummer;
    }

    public String getVoornaam() {
        return Voornaam;
    }

    public void setVoornaam(String voornaam) {
        Voornaam = voornaam;
    }

    public String getNaam() {
        return Naam;
    }

    public void setNaam(String naam) {
        Naam = naam;
    }

    public String getStraat() {
        return Straat;
    }

    public void setStraat(String straat) {
        Straat = straat;
    }

    public String getGemeente() {
        return Gemeente;
    }

    public void setGemeente(String gemeente) {
        Gemeente = gemeente;
    }

    public String getNr() {
        return Nr;
    }

    public void setNr(String nr) {
        Nr = nr;
    }

    public String getPostcode() {
        return Postcode;
    }

    public void setPostcode(String postcode) {
        Postcode = postcode;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }
}
