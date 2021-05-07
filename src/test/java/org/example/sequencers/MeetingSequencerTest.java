package org.example.sequencers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MeetingSequencerTest {

    @BeforeEach
    void setUp() {
        MeetingSequencer.reset();
    }

    @Test
    void nextMeetingId() {
        int expected = 1;
        int actual = MeetingSequencer.nextMeetingId();
        assertEquals(expected, actual);
    }
}