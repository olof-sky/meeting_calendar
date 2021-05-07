package org.example.data;

import org.example.models.AppUser;
import org.example.models.Meeting;
import org.example.models.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class MeetingRepositoryTest {

    private AppUser appUser1 = new AppUser(1, "Olof", "hehehe");
    private AppUser appUser2 = new AppUser(2,"Manne", "hihihi");
    private AppUser appUser3 = new AppUser(3,"Alle", "hohoho");
    private Person person1 = new Person(1, "Olof", "Schylander", "email1@email.com", appUser1);
    private Person person2 = new Person(2, "Manne", "Mannis", "email2@email.com", appUser2);
    private Person person3 = new Person(3, "Alle", "Allis", "email3@email.com", appUser3);
    private LocalDate meetingDate1 = LocalDate.of(2021, 10, 10);
    private LocalTime start1 = LocalTime.of(10,10,10);
    private LocalTime end1 = LocalTime.of(10,20,10);
    private LocalDate meetingDate2 = LocalDate.of(2022, 10, 10);
    private LocalTime start2 = LocalTime.of(11,11,11);
    private LocalTime end2 = LocalTime.of(11,21,11);
    private Meeting meeting1 = new Meeting(1, "Java", meetingDate1, start1, end1, person1);
    private Meeting meeting2 = new Meeting(2, "Java2", meetingDate2, start2, end2, person1);
    private Meeting meeting3 = new Meeting(3, "Java3", meetingDate2, start2, end2, person3);
    private List<Person> attendants1 = new ArrayList<>();
    private List<Person> attendants2 = new ArrayList<>();
    private List<Meeting> meetingList = new ArrayList<>();


    @BeforeEach
    void setUp() {
        MeetingRepository.getInstance().reset();
        attendants1.clear();
        attendants2.clear();
        meetingList.clear();
        attendants1.add(person2);
        attendants2.add(person3);
        meeting1.setAttendants(attendants1);
        meeting2.setAttendants(attendants2);
        meetingList.add(meeting1);
        meetingList.add(meeting2);
        MeetingRepository.getInstance().persist(meeting1);
        MeetingRepository.getInstance().persist(meeting2);
    }

    @Test
    void findAll() {
        List<Meeting> expected = meetingList;
        List<Meeting> actual = MeetingRepository.getInstance().findAll();
        assertEquals(expected, actual);
    }

    @Test
    void findByAttendeePersonId() {
        List<Meeting> expected = new ArrayList<>();
        expected.add(meeting1);
        List<Meeting> actual = MeetingRepository.getInstance().findByAttendeePersonId(2);
        assertEquals(expected, actual);
    }

    @Test
    void findById() {
        Meeting expected = meeting1;
        Meeting actual = MeetingRepository.getInstance().findById(1);
        assertEquals(expected,actual);
    }

    @Test
    void didntFindById() {
        assertNull(MeetingRepository.getInstance().findById(5));
    }

    @Test
    void findByMeetingDate() {
        List<Meeting> expected = new ArrayList<>();
        expected.add(meeting1);
        LocalDate searchDate = LocalDate.of(2021, 10, 10);
        List<Meeting> actual = MeetingRepository.getInstance().findByMeetingDate(searchDate);
        assertEquals(expected, actual);
    }

    @Test
    void findByMeetingsBetween() {
        List<Meeting> expected = new ArrayList<>();
        expected.add(meeting1);
        expected.add(meeting2);
        LocalDate searchDate1 = LocalDate.of(2020, 10, 10);
        LocalDate searchDate2 = LocalDate.of(2024, 11, 9);
        LocalTime searchTime = LocalTime.of(10, 10, 10);
        LocalDateTime searchDateTime1 = LocalDateTime.of(searchDate1, searchTime);
        LocalDateTime searchDateTime2 = LocalDateTime.of(searchDate2, searchTime);
        List<Meeting> actual = MeetingRepository.getInstance().findByMeetingsBetween(searchDateTime1, searchDateTime2);
        assertEquals(expected, actual);
    }

    @Test
    void findByOrganizerPersonId() {
        List<Meeting> expected = new ArrayList<>();
        expected.add(meeting1);
        expected.add(meeting2);
        List<Meeting> actual = MeetingRepository.getInstance().findByOrganizerPersonId(1);
        assertEquals(expected, actual);
    }

    @Test
    void findByTopic() {
        List<Meeting> expected = new ArrayList<>();
        expected.add(meeting2);
        List<Meeting> actual = MeetingRepository.getInstance().findByTopic("Java2");
        assertEquals(expected, actual);
    }

    @Test
    void getMeetingCount() {
        int expected = 2;
        int actual = MeetingRepository.getInstance().getMeetingCount();
        assertEquals(expected, actual);
    }

    @Test
    void persist() {
        MeetingRepository.getInstance().persist(meeting3);
        List<Meeting> expected = new ArrayList<>();
        expected.add(meeting1);
        expected.add(meeting2);
        expected.add(meeting3);
        List<Meeting> actual = MeetingRepository.getInstance().findAll();
        assertEquals(expected, actual);
    }

    @Test
    void removeById() {
        MeetingRepository.getInstance().removeById(2);
        List<Meeting> expected = new ArrayList<>();
        expected.add(meeting1);
        List<Meeting> actual = MeetingRepository.getInstance().findAll();
        assertEquals(expected, actual);
    }

    @Test
    void removeIdDoesntExist() {
        assertFalse(MeetingRepository.getInstance().removeById(5));
    }
}