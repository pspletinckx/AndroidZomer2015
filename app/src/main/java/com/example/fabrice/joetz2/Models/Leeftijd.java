
package com.example.fabrice.joetz2.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Leeftijd {

    @Expose
    private Integer id;
    @Expose
    private Integer vacationId;
    @SerializedName("min_leeftijd")
    @Expose
    private Integer minLeeftijd;
    @SerializedName("max_leeftijd")
    @Expose
    private Integer maxLeeftijd;

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
     *     The vacationId
     */
    public Integer getVacationId() {
        return vacationId;
    }

    /**
     * 
     * @param vacationId
     *     The vacationId
     */
    public void setVacationId(Integer vacationId) {
        this.vacationId = vacationId;
    }

    /**
     * 
     * @return
     *     The minLeeftijd
     */
    public Integer getMinLeeftijd() {
        return minLeeftijd;
    }

    /**
     * 
     * @param minLeeftijd
     *     The min_leeftijd
     */
    public void setMinLeeftijd(Integer minLeeftijd) {
        this.minLeeftijd = minLeeftijd;
    }

    /**
     * 
     * @return
     *     The maxLeeftijd
     */
    public Integer getMaxLeeftijd() {
        return maxLeeftijd;
    }

    /**
     * 
     * @param maxLeeftijd
     *     The max_leeftijd
     */
    public void setMaxLeeftijd(Integer maxLeeftijd) {
        this.maxLeeftijd = maxLeeftijd;
    }

}
