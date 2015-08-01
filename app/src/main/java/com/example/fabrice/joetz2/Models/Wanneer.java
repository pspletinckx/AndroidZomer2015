
package com.example.fabrice.joetz2.Models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wanneer {

    @Expose
    private Integer id;
    @Expose
    private Integer vacation;
    @Expose
    private Integer periode;
    @SerializedName("date_begin")
    @Expose
    private String dateBegin;
    @SerializedName("date_end")
    @Expose
    private String dateEnd;

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
     *     The periode
     */
    public Integer getPeriode() {
        return periode;
    }

    /**
     * 
     * @param periode
     *     The periode
     */
    public void setPeriode(Integer periode) {
        this.periode = periode;
    }

    /**
     * 
     * @return
     *     The dateBegin
     */
    public String getDateBegin() {
        return dateBegin;
    }

    /**
     * 
     * @param dateBegin
     *     The date_begin
     */
    public void setDateBegin(String dateBegin) {
        this.dateBegin = dateBegin;
    }

    /**
     * 
     * @return
     *     The dateEnd
     */
    public String getDateEnd() {
        return dateEnd;
    }

    /**
     * 
     * @param dateEnd
     *     The date_end
     */
    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

}
