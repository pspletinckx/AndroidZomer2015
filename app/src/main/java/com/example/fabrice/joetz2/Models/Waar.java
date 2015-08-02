
package com.example.fabrice.joetz2.Models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Waar {

    @Expose
    private Integer id;
    @Expose
    private Integer vacation;
    @SerializedName("vakantie_domein")
    @Expose
    private String vakantieDomein;
    @Expose
    private String stad;

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
     *     The vakantieDomein
     */
    public String getVakantieDomein() {
        return vakantieDomein;
    }

    /**
     * 
     * @param vakantieDomein
     *     The vakantie_domein
     */
    public void setVakantieDomein(String vakantieDomein) {
        this.vakantieDomein = vakantieDomein;
    }

    /**
     * 
     * @return
     *     The stad
     */
    public String getStad() {
        return stad;
    }

    /**
     * 
     * @param stad
     *     The stad
     */
    public void setStad(String stad) {
        this.stad = stad;
    }

}
