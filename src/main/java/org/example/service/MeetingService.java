package org.example.service;

import org.example.data.MeetingRepository;
import org.example.data.PersonRepository;
import org.example.models.AppUser;
import org.example.models.Meeting;
import org.example.models.Person;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MeetingService {
    private static MeetingService INSTANCE;

    static {
        INSTANCE = new MeetingService();
    }

    public static MeetingService getInstance(){
        return INSTANCE;
    }

    private final List<AppUser> meetingService;

    private MeetingService(){
        meetingService = new ArrayList<>();
    }

    public static void reset() {
        INSTANCE = new MeetingService();
    }

    MeetingRepository meetingRepository;

    public Meeting create(String topic, LocalDate meetingDate, LocalTime start, LocalTime end, Person organizer){
        Meeting meeting = new Meeting(topic, meetingDate, start, end, organizer);
        MeetingRepository.getInstance().persist(meeting);
        return meeting;
    }

    public Meeting create(String topic, LocalDate meetingDate, LocalTime start, LocalTime end, Person organizer, List<Integer> attendantsId){
        Meeting meeting = new Meeting(topic, meetingDate, start, end, organizer);
        for (int i=0; i<= attendantsId.toArray().length; i++){
            Person attendee = PersonRepository.getInstance().findById(i);
            meeting.addAttendant(attendee);
        }
        MeetingRepository.getInstance().persist(meeting);
        return meeting;
    }

    public Meeting update(int id, String topic, LocalDate meetingDate, LocalTime start, LocalTime end){
        for (Meeting meeting : MeetingRepository.getInstance().findAll()){
            if (meeting.getId() == id){
                meeting.setTopic(topic);
                meeting.setMeetingDate(meetingDate);
                meeting.setStart(start);
                meeting.setEnd(end);
                return meeting;
            }
        }return null;
    }

    public Meeting addAttendant(int meetingId, int personId){
        for (Meeting meeting : MeetingRepository.getInstance().findAll()){
            if (meeting.getId() == meetingId){
                meeting.addAttendant(PersonRepository.getInstance().findById(personId));
                return meeting;
            }
        }return null;
    }

    public Meeting removeAttendant(int meetingId, int personId){
            for (Meeting meeting : MeetingRepository.getInstance().findAll()) {
                if (meeting.getId() == meetingId) {
                    for (Person attendant : meeting.getAttendants()){
                        if (attendant.getId() != personId) {meeting.addAttendant(attendant);}
                    }return meeting;
                }
            }return null;
        }

    public List<Meeting> findAll() {
        return meetingRepository.findAll();
    }

    public List<Meeting> findByAttendeePersonId(int id) {
        return meetingRepository.findByAttendeePersonId(id);
    }

    public Meeting findById(int id) {
        return meetingRepository.findById(id);
    }

    public List<Meeting> findByMeetingDate(LocalDate date) {
        return meetingRepository.findByMeetingDate(date);
    }

    public List<Meeting> findByMeetingsBetween(LocalDateTime start, LocalDateTime end) {
        return meetingRepository.findByMeetingsBetween(start, end);
    }

    public List<Meeting> findByOrganizerPersonId(int id) {
        return meetingRepository.findByOrganizerPersonId(id);
    }

    public List<Meeting> findByTopic(String topic) {
        return meetingRepository.findByTopic(topic);
    }

    public boolean removeById(int id) {
        return meetingRepository.removeById(id);
    }
}
