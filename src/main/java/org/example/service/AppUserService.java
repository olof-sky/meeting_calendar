package org.example.service;

import org.example.data.AppUserRepository;
import org.example.models.AppUser;

import java.util.ArrayList;
import java.util.List;

public class AppUserService {

    private static AppUserService INSTANCE;

    static {
        INSTANCE = new AppUserService();
    }

    public static AppUserService getInstance(){
        return INSTANCE;
    }

    private final List<AppUser> appUserService;

    private AppUserService(){
        appUserService = new ArrayList<>();
    }

    public static void reset() {
        INSTANCE = new AppUserService();
    }

    AppUserRepository appUserRepository;

    public AppUser create(String username, String password){
        for (AppUser appUser : AppUserRepository.getInstance().findAll()){
            if (appUser.getUsername().equals(username)) throw new RuntimeException("String username already in use");
        }
        AppUser appUser = new AppUser(username, password);
        AppUserRepository.getInstance().persist(appUser);
        return appUser;
    }


    public AppUser update(int id, String username, String password){
        AppUser returnAppUser;
        for (AppUser appUser : AppUserRepository.getInstance().findAll()){
            if (appUser.getUsername().equals(username)){
                throw new RuntimeException("username already in use");
            }
            else if (appUser.getId() == id){
                appUser.setUsername(username);
                appUser.setPassword(password);
                returnAppUser = appUser;
                return returnAppUser;
            }
        }return null;
    }


    public List<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    public AppUser findById(int id) {
        return appUserRepository.findById(id);
    }

    public AppUser findByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    public int getAppUserCount() {
        return appUserRepository.getAppUserCount();
    }

    public AppUser persist(AppUser appUser) {
        return appUserRepository.persist(appUser);
    }

    public boolean removeById(int id) {
        return appUserRepository.removeById(id);
    }


}
