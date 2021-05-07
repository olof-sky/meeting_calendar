package org.example.models;

import org.example.sequencers.MeetingSequencer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MeetingTest {
    private AppUser appUser1 = new AppUser(1, "olof", "you'llneverknow");
    private AppUser appUser2 = new AppUser(2, "alle", "you'llneverknow");
    private AppUser appUser3 = new AppUser(3, "kalle", "you'llneverknow");
    private AppUser appUser4 = new AppUser(4, "tjalle", "you'llneverknow");
    private String topic = "Java";
    private LocalDate meetingDate = LocalDate.of(2021,02,20);
    private LocalTime start = LocalTime.parse("10:10:10");
    private LocalTime end = LocalTime.parse("10:20:10");
    private Person organizer = new Person(1, "Olof", "Schylander", "email@email.com", appUser1);
    private Person attendant1 = new Person(2, "Alle", "Den första", "email@email.com", appUser2);
    private Person attendant2 = new Person(3, "Kalle", "Den andra", "email@email.com", appUser3);
    private Person attendant3 = new Person(4, "Tjalle", "Den tredje", "email@email.com", appUser4);
    List<Person> attendants = new ArrayList<>(Arrays.asList(attendant1, attendant2));
    Meeting meeting = new Meeting(1, topic, meetingDate, start, end, organizer);

    @BeforeEach
    void setUp() {
        attendants = new ArrayList<>(Arrays.asList(attendant1, attendant2));
        meeting.setAttendants(attendants);
        MeetingSequencer.reset();
    }

    @Test
    void addAttendant() {
        meeting.addAttendant(attendant3);
        List<Person> expected = new ArrayList<>(Arrays.asList(attendant1, attendant2, attendant3));
        List<Person> actual = meeting.getAttendants();
        assertEquals(expected, actual);
    }

    @Test
    void setAttendants() {
        meeting.setAttendants(attendants);
        List<Person> actual = meeting.getAttendants();
        assertEquals(attendants, actual);
    }

    @Test
    void getAttendants() {
        List<Person> actual = meeting.getAttendants();
        assertEquals(attendants, actual);
    }

    @Test
    void getId() {
        int expected = 1;
        int actual = meeting.getId();
        assertEquals(expected, actual);
    }

    @Test
    void setTopic() {
        meeting.setTopic(topic);
        String actual = meeting.getTopic();
        assertEquals(topic, actual);
    }

    @Test
    void getTopic() {
        String actual = meeting.getTopic();
        assertEquals(actual, topic);
    }

    @Test
    void getMeetingDate() {
        LocalDate actual = meeting.getMeetingDate();
        assertEquals(actual, meetingDate);
    }

    @Test
    void setMeetingDate() {
        meeting.setMeetingDate(meetingDate);
        LocalDate actual = meeting.getMeetingDate();
        assertEquals(actual, meetingDate);
    }

    @Test
    void getStart() {
        meeting.setStart(start);
        LocalTime actual = meeting.getStart();
        assertEquals(actual, start);
    }

    @Test
    void setStart() {
        meeting.setStart(start);
        LocalTime actual = meeting.getStart();
        assertEquals(actual, start);
    }

    @Test
    void getEnd() {
        meeting.setEnd(end);
        LocalTime actual = meeting.getEnd();
        assertEquals(actual, end);
    }

    @Test
    void setEnd() {
        meeting.setEnd(end);
        LocalTime actual = meeting.getEnd();
        assertEquals(actual, end);
    }

    @Test
    void setOrganizer() {
        meeting.setOrganizer(organizer);
        Person actual = meeting.getOrganizer();
        assertEquals(actual, organizer);
    }

    @Test
    void getOrganizer() {
        Person actual = meeting.getOrganizer();
        assertEquals(actual, organizer);
    }
}