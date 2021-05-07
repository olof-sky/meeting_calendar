package org.example.models;

import org.example.App;
import org.example.sequencers.AppUserSequencer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppUserTest {

    private String username = "olofsky";
    private String password = "you'llneverknow";
    private AppUser appUser = new AppUser(1, username, password);

    @BeforeEach
    void setUp() {
        appUser.setUsername(username);
        appUser.setPassword(password);
        AppUserSequencer.reset();
    }

    @Test
    void getId() {
        int expected = 1;
        int actual = appUser.getId();
        assertEquals(expected, actual);
    }

    @Test
    void getUsername() {
        String expected = "olofsky";
        String actual = appUser.getUsername();
        assertEquals(expected, actual);
    }

    @Test
    void setUsername() {
        appUser.setUsername(username);
        String expected = "olofsky";
        String actual = appUser.getUsername();
        assertEquals(expected, actual);
    }

    @Test
    void getPassword() {
        String expected = "you'llneverknow";
        String actual = appUser.getPassword();
        assertEquals(expected, actual);
    }

    @Test
    void setPassword() {
        appUser.setPassword(password);
        String expected = "you'llneverknow";
        String actual = appUser.getPassword();
        assertEquals(expected, actual);
    }
}