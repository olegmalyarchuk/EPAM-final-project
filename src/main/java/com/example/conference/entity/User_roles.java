package com.example.conference.entity;

import java.io.Serializable;

public class User_roles implements Serializable {
    private static final long serialVersionUID = 382926644813433707L;

    private int role_id;
    private String role_description;

    public User_roles() {
    }

    public User_roles(int role_id, String role_description) {
        this.role_id = role_id;
        this.role_description = role_description;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getRole_description() {
        return role_description;
    }

    public void setRole_description(String role_description) {
        this.role_description = role_description;
    }

    @Override
    public String toString() {
        return "User_roles{" +
                "role_id=" + role_id +
                ", role_description='" + role_description + '\'' +
                '}';
    }
}
