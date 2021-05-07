package org.example.models;

import org.example.sequencers.AppUserSequencer;

import java.util.Objects;

public class AppUser {
    private int id;
    private String username;
    private String password;

    public AppUser(int id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public AppUser(String username, String password){
        this(AppUserSequencer.nextAppUserId(), username, password);
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null || username.isEmpty()) throw new RuntimeException("String username was null");
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.isEmpty()) throw new RuntimeException("String password was null");
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return id == appUser.id && username.equals(appUser.username) && password.equals(appUser.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }
}
