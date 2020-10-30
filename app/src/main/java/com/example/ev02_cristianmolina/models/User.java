package com.example.ev02_cristianmolina.models;

import java.io.Serializable;

public class User implements Serializable {

    private long id;
    private String user;
    private String password;

    public User() {}

    public User(String user, String password) {
        this.id = 0L;
        this.user = user;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
