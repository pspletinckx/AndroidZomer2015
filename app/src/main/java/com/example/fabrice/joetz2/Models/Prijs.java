
package com.example.fabrice.joetz2.Models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Prijs {

    @Expose
    private Integer id;
    @Expose
    private Integer vacation;
    @Expose
    private Integer basis;
    @SerializedName("ster_enkel")
    @Expose
    private Integer sterEnkel;
    @SerializedName("ster_dubbel")
    @Expose
    private Integer sterDubbel;

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
    public Integer getBasis() {
        return basis;
    }

    /**
     * 
     * @param basis
     *     The basis
     */
    public void setBasis(Integer basis) {
        this.basis = basis;
    }

    /**
     * 
     * @return
     *     The sterEnkel
     */
    public Integer getSterEnkel() {
        return sterEnkel;
    }

    /**
     * 
     * @param sterEnkel
     *     The ster_enkel
     */
    public void setSterEnkel(Integer sterEnkel) {
        this.sterEnkel = sterEnkel;
    }

    /**
     * 
     * @return
     *     The sterDubbel
     */
    public Integer getSterDubbel() {
        return sterDubbel;
    }

    /**
     * 
     * @param sterDubbel
     *     The ster_dubbel
     */
    public void setSterDubbel(Integer sterDubbel) {
        this.sterDubbel = sterDubbel;
    }

}
