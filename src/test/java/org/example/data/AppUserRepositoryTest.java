package org.example.data;

import org.example.models.AppUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class AppUserRepositoryTest {

    private AppUser appUser1 = new AppUser(1, "Olof", "hehehe");
    private AppUser appUser2 = new AppUser(2,"Manne", "hihihi");
    private AppUser appUser3 = new AppUser(3,"Alle", "hohoho");
    private AppUser appUser4 = new AppUser(4,"Tjorven", "hahaha");

    @BeforeEach
    void setUp(){
        AppUserRepository.reset();
        AppUserRepository.getInstance().persist(appUser1);
        AppUserRepository.getInstance().persist(appUser2);
        AppUserRepository.getInstance().persist(appUser3);
    }

    @Test
    void findAll() {
        List<AppUser> appUserList = new ArrayList<>();
        appUserList.add(appUser1);
        appUserList.add(appUser2);
        appUserList.add(appUser3);
        List<AppUser> expected = appUserList;
        List<AppUser> actual = AppUserRepository.getInstance().findAll();
        assertEquals(expected, actual);
    }

    @Test
    void findById() {
        AppUser actual = AppUserRepository.getInstance().findById(2);
        AppUser expected = appUser2;
        assertEquals(expected, actual);
    }

    @Test
    void idNotFound() {
        AppUser actual = AppUserRepository.getInstance().findById(27);
        assertEquals(null, actual);
    }

    @Test
    void findByUsername() {
        AppUser actual = AppUserRepository.getInstance().findByUsername("Olof");
        AppUser expected = appUser1;
        assertEquals(expected, actual);
    }

    @Test
    void didNotFindByUsername() {
        assertNull(AppUserRepository.getInstance().findByUsername("Finnsej"));
    }

    @Test
    void getAppUserCount() {
        int actual = AppUserRepository.getInstance().getAppUserCount();
        assertEquals(3, actual);
    }

    @Test
    void persist() {
        List<AppUser> appUserList = AppUserRepository.getInstance().findAll();
        appUserList.add(appUser4);
        AppUserRepository.getInstance().persist(appUser4);
        List<AppUser> expected =  appUserList;
        List<AppUser> actual = AppUserRepository.getInstance().findAll();
        assertEquals(expected, actual);
    }

    @Test
    void removeById() {
        assertTrue(AppUserRepository.getInstance().removeById(3));
    }

    @Test
    void removeIdDoesntExist() {
        assertFalse(AppUserRepository.getInstance().removeById(5));
    }
}