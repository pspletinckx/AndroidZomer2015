package com.example.fabrice.joetz2.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Fabrice on 25/08/2015.
 */
public class MyselfModel {
    @SerializedName("userName")
    private String userName;
    @SerializedName("role")
    private String role;

    public MyselfModel(String userName, String role) {
        this.userName = userName;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "MyselfModel{" +
                "userName='" + userName + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
