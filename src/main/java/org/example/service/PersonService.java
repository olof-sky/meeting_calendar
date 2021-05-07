package org.example.service;

import org.example.data.PersonRepository;
import org.example.models.AppUser;
import org.example.models.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonService {
    private static PersonService INSTANCE;

    static {
        INSTANCE = new PersonService();
    }

    public static PersonService getInstance(){
        return INSTANCE;
    }

    private final List<AppUser> personService;

    private PersonService(){
        personService = new ArrayList<>();
    }

    public static void reset() {
        INSTANCE = new PersonService();
    }

    PersonRepository personRepository;

    public Person create(String firstName, String lastName, String email, AppUser userCredentials){
        if (userCredentials.getUsername() == null){
            throw new RuntimeException("username is null");
        }
        else if (userCredentials.getPassword() == null){
            throw new RuntimeException("password is null");
        }
        for (Person person : PersonRepository.getInstance().findAll()) {
            if (person.getUserCredentials().getUsername().equals(userCredentials.getUsername())){
                throw new RuntimeException("username already in personRepository");}
            else if (person.getEmail().equals(email)){
                throw new RuntimeException("email already in personRepository");}
        }
        Person person = new Person(firstName, lastName, email, userCredentials);
        PersonRepository.getInstance().persist(person);
        return person;
    }

    public Person update(int id, String firstName, String lastName, String email){
        Person returnPerson;
        for (Person person : PersonRepository.getInstance().findAll()) {
            if (person.getEmail().equals(email)){
                throw new RuntimeException("email already in use");
            }
            else if(person.getId() == id){
                person.setFirstName(firstName);
                person.setLastName(lastName);
                returnPerson = person;
                return returnPerson;
            }
        }return null;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    public List<Person> findByFirstName(String firstName) {
        return personRepository.findByFirstName(firstName);
    }

    public Person findById(int id) {
        return personRepository.findById(id);
    }

    public List<Person> findByLastName(String lastName) {
        return personRepository.findByLastName(lastName);
    }

    public Person findByUsername(String username) {
        return personRepository.findByUsername(username);
    }

    public boolean removeById(int id) {
        return personRepository.removeById(id);
    }
}
