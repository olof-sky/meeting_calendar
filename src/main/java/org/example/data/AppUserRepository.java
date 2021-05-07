package org.example.data;

import org.example.models.AppUser;

import java.util.ArrayList;
import java.util.List;

public class AppUserRepository {

    private static AppUserRepository INSTANCE;

    static {
        INSTANCE = new AppUserRepository();
    }

    public static AppUserRepository getInstance(){
        return INSTANCE;
    }

    private final List<AppUser> appUserStorage;

    private AppUserRepository(){
        appUserStorage = new ArrayList<>();
    }

    public static void reset() {
        INSTANCE = new AppUserRepository();
    }

    public List<AppUser> findAll(){
        return appUserStorage;
    }

    public AppUser findById(int id){
        for (AppUser appUser : appUserStorage){
            if(appUser.getId() == id){
                return appUser;
            }
        }
        return null;
    }

    public AppUser findByUsername(String username){
        for (AppUser appuser : appUserStorage){
            if (appuser.getUsername().equals(username)){
                return appuser;
            }
        }
        return null;
    }

    public int getAppUserCount(){
        return appUserStorage.size();
    }

    public AppUser persist(AppUser appUser){
        if(!appUserStorage.contains(appUser)){
            appUserStorage.add(appUser);
        }
        return appUser;
    }

    public boolean removeById(int id){
        for (AppUser appUser : appUserStorage){
            if(appUser.getId() == id){
                return appUserStorage.remove(appUser);
            }
        }
        return false;
    }
}
