
package com.example.fabrice.joetz2.Models;

import com.google.gson.annotations.Expose;

public class Inbegrepen {

    @Expose
    private Integer id;
    @Expose
    private Integer vacation;
    @Expose
    private String basis;

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
    public String getBasis() {
        return basis;
    }

    /**
     * 
     * @param basis
     *     The basis
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }

}
