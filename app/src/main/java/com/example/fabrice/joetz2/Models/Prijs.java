
package com.example.fabrice.joetz2.Models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Prijs {

    @Expose
    private Integer id;
    @Expose
    private Integer vacation;
    @Expose
    private Double basis;
    @SerializedName("ster_enkel")
    @Expose
    private Double sterEnkel;
    @SerializedName("ster_dubbel")
    @Expose
    private Double sterDubbel;

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
     *     The basis
     */
    public Double getBasis() {
        return basis;
    }

    /**
     * 
     * @param basis
     *     The basis
     */
    public void setBasis(Double basis) {
        this.basis = basis;
    }

    /**
     * 
     * @return
     *     The sterEnkel
     */
    public Double getSterEnkel() {
        return sterEnkel;
    }

    /**
     * 
     * @param sterEnkel
     *     The ster_enkel
     */
    public void setSterEnkel(Double sterEnkel) {
        this.sterEnkel = sterEnkel;
    }

    /**
     * 
     * @return
     *     The sterDubbel
     */
    public Double getSterDubbel() {
        return sterDubbel;
    }

    /**
     * 
     * @param sterDubbel
     *     The ster_dubbel
     */
    public void setSterDubbel(Double sterDubbel) {
        this.sterDubbel = sterDubbel;
    }

}
