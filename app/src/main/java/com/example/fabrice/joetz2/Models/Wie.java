
package com.example.fabrice.joetz2.Models;

import com.google.gson.annotations.Expose;

public class Wie {

    @Expose
    private Integer id;
    @Expose
    private Integer vacation;
    @Expose
    private Integer groep;
    @Expose
    private Leeftijd_ leeftijd;

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
     *     The groep
     */
    public Integer getGroep() {
        return groep;
    }

    /**
     * 
     * @param groep
     *     The groep
     */
    public void setGroep(Integer groep) {
        this.groep = groep;
    }

    /**
     * 
     * @return
     *     The leeftijd
     */
    public Leeftijd_ getLeeftijd() {
        return leeftijd;
    }

    /**
     * 
     * @param leeftijd
     *     The leeftijd
     */
    public void setLeeftijd(Leeftijd_ leeftijd) {
        this.leeftijd = leeftijd;
    }

}
