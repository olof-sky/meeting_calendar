package org.example.data;

import org.example.models.AppUser;
import org.example.models.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonRepositoryTest {
    private AppUser appUser1 = new AppUser(1, "Olof", "hehehe");
    private AppUser appUser2 = new AppUser(2,"Manne", "hihihi");
    private AppUser appUser3 = new AppUser(3,"Alle", "hohoho");
    private Person person1 = new Person(1, "Olof", "Schylander", "email1@email.com", appUser1);
    private Person person2 = new Person(2, "Manne", "Mannis", "email2@email.com", appUser2);
    private Person person3 = new Person(3, "Alle", "Allis", "email3@email.com", appUser3);


    @BeforeEach
    void setUp(){
        PersonRepository.reset();
        PersonRepository.getInstance().persist(person1);
        PersonRepository.getInstance().persist(person2);
    }

    @Test
    void findAll() {
        List<Person> expected = new ArrayList<>();
        expected.add(person1);
        expected.add(person2);
        List<Person> actual = PersonRepository.getInstance().findAll();
        assertEquals(expected, actual);
    }

    @Test
    void findByEmail() {
        Person expected = person1;
        Person actual = PersonRepository.getInstance().findByEmail("email1@email.com");
        assertEquals(expected, actual);
    }

    @Test
    void emailDoesntExist() {
        assertNull(PersonRepository.getInstance().findByEmail("doesnt@exist.com"));
    }

    @Test
    void findByFirstName() {
        List<Person> expected = new ArrayList<>();
        expected.add(person1);
        List<Person> actual = PersonRepository.getInstance().findByFirstName("Olof");
        assertEquals(expected, actual);
    }

    @Test
    public void firstNameDoesntExist() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            PersonRepository.getInstance().findByFirstName("Doesntexist");
        });

        String expectedMessage = "String firstName is not in personStorage";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void findById() {
        Person expected = person1;
        Person actual = PersonRepository.getInstance().findById(1);
        assertEquals(expected, actual);
    }

    @Test
    void idDoesntExist(){
        assertNull(PersonRepository.getInstance().findById(5));
    }

    @Test
    void findByLastName() {
        List<Person> expected = new ArrayList<>();
        expected.add(person1);
        List<Person> actual = PersonRepository.getInstance().findByLastName("Schylander");
        assertEquals(expected, actual);
    }

    @Test
    public void lastNameDoesntExist() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            PersonRepository.getInstance().findByLastName("Doesntexist");
        });

        String expectedMessage = "String firstName is not in personStorage";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void findByUsername() {
        Person expected = person1;
        Person actual = PersonRepository.getInstance().findByUsername("Olof");
        assertEquals(expected, actual);
    }

    @Test
    void usernameDoesntExist() {
        assertNull(PersonRepository.getInstance().findByUsername("Doesntexist"));
    }

    @Test
    void getPeopleCount() {
        int expected = 2;
        int actual = PersonRepository.getInstance().getPeopleCount();
        assertEquals(expected, actual);
    }

    @Test
    void persist() {
        List<Person> expected = new ArrayList<>();
        expected.add(person1);
        expected.add(person2);
        expected.add(person3);
        PersonRepository.getInstance().persist(person3);
        List<Person> actual = PersonRepository.getInstance().findAll();
        assertEquals(expected, actual);
    }

    @Test
    void removeById() {
        List<Person> expected = new ArrayList<>();
        expected.add(person1);
        PersonRepository.getInstance().removeById(2);
        List<Person> actual = PersonRepository.getInstance().findAll();
        assertEquals(expected, actual);
    }

    @Test
    void removeIdDoesntExist() {
        assertFalse(PersonRepository.getInstance().removeById(56));
    }
}