package org.example.sequencers;

public class PersonSequencer {
    static int personID;

    public static int nextPersonId(){
        return personID = personID + 1;
    }

    public static void reset(){
        personID = 0;
    }
}
