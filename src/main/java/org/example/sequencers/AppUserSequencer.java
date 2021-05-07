package org.example.sequencers;

public class AppUserSequencer {

    static int appUserID;

    public static int nextAppUserId(){
        return appUserID = appUserID + 1;
    }

    public static void reset(){
        appUserID = 0;
    }
}