
package com.example.fabrice.joetz2.Models;

import java.util.ArrayList;
import java.util.List;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vacation {

    @Expose
    private Integer id;
    @Expose
    private String titel;
    @Expose
    private Leeftijd leeftijd;
    @Expose
    private Waar waar;
    @Expose
    private Wanneer wanneer;
    @SerializedName("aantal_deelnemers")
    @Expose
    private Integer aantalDeelnemers;
    @Expose
    private Prijs prijs;
    @Expose
    private List<Inbegrepen> inbegrepen = new ArrayList<Inbegrepen>();
    @Expose
    private Informatie informatie;
    @Expose
    private List<Opmerking> opmerking = new ArrayList<Opmerking>();
    @Expose
    private String promotext;
    @Expose
    private List<Foto> foto = new ArrayList<Foto>();
    @SerializedName("cover_foto")
    @Expose
    private CoverFoto coverFoto;
    @SerializedName("fiscaal_voordeel")
    @Expose
    private Boolean fiscaalVoordeel;

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The titel
     */
    public String getTitel() {
        return titel;
    }

    /**
     * 
     * @param titel
     *     The titel
     */
    public void setTitel(String titel) {
        this.titel = titel;
    }

    /**
     * 
     * @return
     *     The leeftijd
     */
    public Leeftijd getLeeftijd() {
        return leeftijd;
    }

    /**
     * 
     * @param leeftijd
     *     The leeftijd
     */
    public void setLeeftijd(Leeftijd leeftijd) {
        this.leeftijd = leeftijd;
    }

    /**
     * 
     * @return
     *     The waar
     */
    public Waar getWaar() {
        return waar;
    }

    /**
     * 
     * @param waar
     *     The waar
     */
    public void setWaar(Waar waar) {
        this.waar = waar;
    }

    /**
     * 
     * @return
     *     The wanneer
     */
    public Wanneer getWanneer() {
        return wanneer;
    }

    /**
     * 
     * @param wanneer
     *     The wanneer
     */
    public void setWanneer(Wanneer wanneer) {
        this.wanneer = wanneer;
    }

    /**
     * 
     * @return
     *     The aantalDeelnemers
     */
    public Integer getAantalDeelnemers() {
        return aantalDeelnemers;
    }

    /**
     * 
     * @param aantalDeelnemers
     *     The aantal_deelnemers
     */
    public void setAantalDeelnemers(Integer aantalDeelnemers) {
        this.aantalDeelnemers = aantalDeelnemers;
    }

    /**
     * 
     * @return
     *     The prijs
     */
    public Prijs getPrijs() {
        return prijs;
    }

    /**
     * 
     * @param prijs
     *     The prijs
     */
    public void setPrijs(Prijs prijs) {
        this.prijs = prijs;
    }

    /**
     * 
     * @return
     *     The inbegrepen
     */
    public List<Inbegrepen> getInbegrepen() {
        return inbegrepen;
    }

    /**
     * 
     * @param inbegrepen
     *     The inbegrepen
     */
    public void setInbegrepen(List<Inbegrepen> inbegrepen) {
        this.inbegrepen = inbegrepen;
    }

    /**
     * 
     * @return
     *     The informatie
     */
    public Informatie getInformatie() {
        return informatie;
    }

    /**
     * 
     * @param informatie
     *     The informatie
     */
    public void setInformatie(Informatie informatie) {
        this.informatie = informatie;
    }

    /**
     * 
     * @return
     *     The opmerking
     */
    public List<Opmerking> getOpmerking() {
        return opmerking;
    }

    /**
     * 
     * @param opmerking
     *     The opmerking
     */
    public void setOpmerking(List<Opmerking> opmerking) {
        this.opmerking = opmerking;
    }

    /**
     * 
     * @return
     *     The promotext
     */
    public String getPromotext() {
        return promotext;
    }

    /**
     * 
     * @param promotext
     *     The promotext
     */
    public void setPromotext(String promotext) {
        this.promotext = promotext;
    }

    /**
     * 
     * @return
     *     The foto
     */
    public List<Foto> getFoto() {
        return foto;
    }

    /**
     * 
     * @param foto
     *     The foto
     */
    public void setFoto(List<Foto> foto) {
        this.foto = foto;
    }

    /**
     * 
     * @return
     *     The coverFoto
     */
    public CoverFoto getCoverFoto() {
        return coverFoto;
    }

    /**
     * 
     * @param coverFoto
     *     The cover_foto
     */
    public void setCoverFoto(CoverFoto coverFoto) {
        this.coverFoto = coverFoto;
    }

    /**
     * 
     * @return
     *     The fiscaalVoordeel
     */
    public Boolean getFiscaalVoordeel() {
        return fiscaalVoordeel;
    }

    /**
     * 
     * @param fiscaalVoordeel
     *     The fiscaal_voordeel
     */
    public void setFiscaalVoordeel(Boolean fiscaalVoordeel) {
        this.fiscaalVoordeel = fiscaalVoordeel;
    }

}
