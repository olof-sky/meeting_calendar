package org.example.models;

import org.example.App;
import org.example.sequencers.PersonSequencer;

import java.util.Objects;

public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private AppUser userCredentials;

    public Person(int id, String firstName, String lastName, String email, AppUser userCredentials){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userCredentials = userCredentials;
    }

    public Person(String firstName, String lastName, String email, AppUser userCredentials){this(PersonSequencer.nextPersonId(), firstName, lastName, email, userCredentials);}

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) throw new RuntimeException("String firstName was null");
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) throw new RuntimeException("String lastName was null");
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isEmpty()) throw new RuntimeException("String email was null");
        this.email = email;
    }

    public AppUser getUserCredentials() {
        return userCredentials;
    }

    public void setUserCredentials(AppUser userCredentials) {
        if (userCredentials == null) throw new RuntimeException("userCredentials was null");
        this.userCredentials = userCredentials;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && firstName.equals(person.firstName) && lastName.equals(person.lastName) && email.equals(person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email);
    }
}
