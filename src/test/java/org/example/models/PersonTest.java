package org.example.models;

import org.example.sequencers.MeetingSequencer;
import org.example.sequencers.PersonSequencer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    private String firstName = "Olof";
    private String lastName = "Schylander";
    private String email = "email@email.com";
    private AppUser appUser = new AppUser(1, "username", "password");
    Person olof = new Person(1, firstName, lastName, email, appUser);

    @BeforeEach
    void setUp() {
        PersonSequencer.reset();
    }

    @Test
    void getId() {
        int expected = 1;
        int actual = olof.getId();
        assertEquals(expected, actual);
    }

    @Test
    void getFirstName() {
        String expected = firstName;
        String actual = olof.getFirstName();
        assertEquals(expected, actual);
    }

    @Test
    void setFirstName() {
        olof.setFirstName(firstName);
        String expected = firstName;
        String actual = olof.getFirstName();
        assertEquals(expected, actual);
    }

    @Test
    void getLastName() {
        String expected = lastName;
        String actual = olof.getLastName();
        assertEquals(expected, actual);
    }

    @Test
    void setLastName() {
        olof.setLastName(lastName);
        String expected = lastName;
        String actual = olof.getLastName();
        assertEquals(expected, actual);
    }

    @Test
    void getEmail() {
        String expected = email;
        String actual = olof.getEmail();
        assertEquals(expected, actual);
    }

    @Test
    void setEmail() {
        olof.setEmail(email);
        String expected = email;
        String actual = olof.getEmail();
        assertEquals(expected, actual);

    }

    @Test
    void getUserCredentials() {
        AppUser expected = appUser;
        AppUser actual = olof.getUserCredentials();
        assertEquals(expected, actual);
    }

    @Test
    void setUserCredentials() {
        olof.setUserCredentials(appUser);
        AppUser expected = appUser;
        AppUser actual = olof.getUserCredentials();
        assertEquals(expected, actual);
    }
}