package org.example.data;
import org.example.models.Person;
import java.util.ArrayList;
import java.util.List;

public class PersonRepository {

    private static PersonRepository INSTANCE;

    static {
        INSTANCE = new PersonRepository();
    }

    public static PersonRepository getInstance(){
        return INSTANCE;
    }

    private final List<Person> personStorage;

    private PersonRepository(){
        personStorage = new ArrayList<>();

    }

    public static void reset() {
        INSTANCE = new PersonRepository();
    }

    public List<Person> findAll(){
        return personStorage;
    }

    public Person findByEmail(String email){
        for (Person person : personStorage){
            if (person.getEmail().equals(email)){
                return person;
            }
        }
        return null;
    }

    public List<Person> findByFirstName(String firstName){
        List<Person> personList = new ArrayList<>();
        for (Person person : personStorage){
            if (person.getFirstName().equals(firstName)){
                personList.add(person);
            }
        }
        if (personList.isEmpty()){
            throw new RuntimeException("String firstName is not in personStorage");
        }
        return personList;
    }

    public Person findById(int id){
        for (Person person : personStorage){
            if (person.getId() == id){
                return person;
            }
        }
        return null;
    }

    public List<Person> findByLastName(String lastName){
        List<Person> personList = new ArrayList<>();
        for (Person person : personStorage) {
            if (person.getLastName().equals(lastName)) {
                personList.add(person);
            }
        }
        if (personList.isEmpty()) {
            throw new RuntimeException("String firstName is not in personStorage");
        }
        return personList;
    }

    public Person findByUsername(String username) {
        for (Person person : personStorage) {
            if (person.getUserCredentials().getUsername() == username) {
                return person;
            }
        }
        return null;
    }

    public int getPeopleCount(){
        return personStorage.size();
    }

    public Person persist(Person person){
        if(!personStorage.contains(person)){
            personStorage.add(person);
        }
        return person;
    }

    public boolean removeById(int id){
        for (Person person : personStorage){
            if (person.getId() == id){
                return personStorage.remove(person);
            }
        }
        return false;
    }
}