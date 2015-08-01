
package com.example.fabrice.joetz2.Models;


import com.google.gson.annotations.Expose;


public class Informatie {

    @Expose
    private Integer id;
    @Expose
    private Integer vacation;
    @Expose
    private String tel;
    @Expose
    private String email;

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
     *     The tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * 
     * @param tel
     *     The tel
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 
     * @return
     *     The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email
     *     The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
