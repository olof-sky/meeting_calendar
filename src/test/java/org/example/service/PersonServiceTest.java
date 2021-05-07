package org.example.service;

import org.example.data.PersonRepository;
import org.example.models.AppUser;
import org.example.models.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonServiceTest {

    private String password = "password";
    private AppUser appUser = new AppUser(1, "olof", password);
    private Person person = new Person(1, "olof", "schylander", "email@email.com", appUser);
    private AppUser appUser2 = new AppUser(2, "alle", password);
    private Person sameUsernamePerson = new Person(2, "alle", "schylander", "email@email.com", appUser2);
    private AppUser appUser3 = new AppUser(3, "manne", password);
    private Person sameEmailPerson = new Person(3, "manne", "schylander", "same@email.com", appUser3);
    private AppUser appUser4 = new AppUser(4, "mannis", password);
    private AppUser appUser5 = new AppUser(5, null, password);
    private AppUser appUser6 = new AppUser(6, "manneeeee", null);

    @BeforeEach
    void setUp(){
        PersonRepository.reset();
        PersonService.reset();
        PersonRepository.getInstance().persist(sameUsernamePerson);
        PersonRepository.getInstance().persist(sameEmailPerson);
    }

    @Test
    void create() {
        assertEquals(PersonService.getInstance().create("olof", "schylander", "olof@email.com", appUser).getUserCredentials().getUsername(), person.getUserCredentials().getUsername());
    }

    @Test
    void createThrowRuntimeExceptionUsername(){
        Exception exception = assertThrows(RuntimeException.class, () -> {
            PersonService.getInstance().create("alle", "schylander", "email@email.com", appUser2);
        });

        String expectedMessage = "username already in personRepository";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void createThrowRuntimeExceptionEmail(){
        Exception exception = assertThrows(RuntimeException.class, () -> {
            PersonService.getInstance().create("manne", "schylander", "same@email.com", appUser4);
        });

        String expectedMessage = "email already in personRepository";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void createThrowRuntimeException_UsernameIsNull(){
        Exception exception = assertThrows(RuntimeException.class, () -> {
            PersonService.getInstance().create("manneee", "schylanderrr", "email5@email.com", appUser5);
        });

        String expectedMessage = "username is null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void createThrowRuntimeException_PasswordIsNull(){
        Exception exception = assertThrows(RuntimeException.class, () -> {
            PersonService.getInstance().create("manneee", "schylanderrr", "email5@email.com", appUser6);
        });

        String expectedMessage = "password is null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void update() {
        PersonService.getInstance().update(2, "update", "updated", "updated@update.com");
        assertEquals("update", PersonRepository.getInstance().findById(2).getFirstName());
    }

    @Test
    void updateEmailAreadyInUse() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            PersonService.getInstance().update(3, "update", "updated", "same@email.com");
        });

        String expectedMessage = "email already in use";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}