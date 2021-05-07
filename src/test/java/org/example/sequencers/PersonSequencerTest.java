package org.example.sequencers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonSequencerTest {

    @BeforeEach
    void setUp() {
        PersonSequencer.reset();
    }
    @Test
    void nextPersonId() {
        int expected = 1;
        int actual = PersonSequencer.nextPersonId();
        assertEquals(expected, actual);
    }
}