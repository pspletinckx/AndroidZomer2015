
package com.example.fabrice.joetz2.Models;


import com.google.gson.annotations.Expose;

public class Foto {

    @Expose
    private Integer id;
    @Expose
    private Integer vacation;
    @Expose
    private String titel;
    @Expose
    private String beschrijving;
    @Expose
    private String locatie;

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
     *     The vacation
     */
    public Integer getVacation() {
        return vacation;
    }

    /**
     * 
     * @param vacation
     *     The vacation
     */
    public void setVacation(Integer vacation) {
        this.vacation = vacation;
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
     *     The beschrijving
     */
    public String getBeschrijving() {
        return beschrijving;
    }

    /**
     * 
     * @param beschrijving
     *     The beschrijving
     */
    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    /**
     * 
     * @return
     *     The locatie
     */
    public String getLocatie() {
        return locatie;
    }

    /**
     * 
     * @param locatie
     *     The locatie
     */
    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

}
