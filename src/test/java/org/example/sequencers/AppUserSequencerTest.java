package org.example.sequencers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppUserSequencerTest {

    @BeforeEach
    void setUp() {
        AppUserSequencer.reset();
    }

    @Test
    void nextAppUserId() {
        int expected = 1;
        int actual = AppUserSequencer.nextAppUserId();
        assertEquals(expected, actual);
    }
}