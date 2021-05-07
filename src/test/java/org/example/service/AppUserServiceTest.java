package org.example.service;

import org.example.data.AppUserRepository;
import org.example.models.AppUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppUserServiceTest {

    private String username = "olofsky";
    private String password = "password";
    private AppUser appUser = new AppUser(3, "olofsky2", "password");

    //Remaining tests already implemented in AppUserRepositoryTest
    AppUserRepository appUserRepository;

    @BeforeEach
    void setUp(){
        AppUserService.reset();
        appUserRepository.reset();
        appUserRepository.getInstance().persist(appUser);
    }

    @Test
    void create() {
        AppUser appUser = AppUserService.getInstance().create(username, password);
        assertTrue(appUserRepository.getInstance().findByUsername(username).equals(appUser));
    }

    @Test
    void canNotCreateDuplicate() {
        {
            Exception exception = assertThrows(RuntimeException.class, () -> {
                AppUserService.getInstance().create("olofsky2", "password");
            });

            String expectedMessage = "String username already in use";
            String actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    void updateUsernameAlreadyInUse() {
        {
            Exception exception = assertThrows(RuntimeException.class, () -> {
                AppUserService.getInstance().update(3, "olofsky2", "newPassword");
            });

            String expectedMessage = "username already in use";
            String actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    void updateIdAssertNull(){
        assertNull(AppUserService.getInstance().update(6, "olofsky22", "newPassword"));
    }

    @Test
    void update() {
        AppUserService.getInstance().update(3, "update", "password");
    }
}