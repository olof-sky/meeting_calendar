package org.example.data;

import org.example.models.Meeting;
import org.example.models.Person;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MeetingRepository {

    private static MeetingRepository INSTANCE;

    static {
        INSTANCE = new MeetingRepository();
    }

    public static MeetingRepository getInstance() {
        return INSTANCE;
    }

    private final List<Meeting> meetingStorage;

    private MeetingRepository() {
        meetingStorage = new ArrayList<>();

    }

    public static void reset() {
        INSTANCE = new MeetingRepository();
    }

    public List<Meeting> findAll() {
        return meetingStorage;
    }

    public List<Meeting> findByAttendeePersonId(int id) {
        List<Meeting> meetingList = new ArrayList<>();
        for (Meeting meeting : meetingStorage) {
            for (Person person : meeting.getAttendants()){
                if (person.getId() == id){
                    meetingList.add(meeting);
                }
            }
        }
        if (meetingList.isEmpty()) throw new RuntimeException("int id is not in meetingStorage");
        return meetingList;
    }

    public Meeting findById(int id){
        for (Meeting meeting : meetingStorage){
            if (meeting.getId() == id){
                return meeting;
            }
        }
        return null;
    }

    public List<Meeting> findByMeetingDate(LocalDate date){
        List<Meeting> meetingList = new ArrayList<>();
        for (Meeting meeting : meetingStorage){
            if (meeting.getMeetingDate().equals(date)){
                meetingList.add(meeting);
            }
        }
        if (meetingList.isEmpty()) throw new RuntimeException("LocalDate date is not in meetingStorage");
        return meetingList;
    }

    public List<Meeting> findByMeetingsBetween(LocalDateTime start, LocalDateTime end){
        List<Meeting> meetingList = new ArrayList<>();
        for (Meeting meeting : meetingStorage){
            LocalDate date = meeting.getMeetingDate();
            LocalTime startTime = meeting.getStart();
            LocalTime endTime = meeting.getEnd();
            LocalDateTime dateTimeStart = LocalDateTime.of(date, startTime);
            LocalDateTime dateTimeEnd = LocalDateTime.of(date, endTime);
            if (start.isEqual(dateTimeStart) || start.isBefore(dateTimeStart) && end.isEqual(dateTimeEnd) || end.isAfter(dateTimeEnd)){
                meetingList.add(meeting);
            }
        }
        if (meetingList.isEmpty()) throw new RuntimeException("Meeting between LocalDateTime start/end is not in meetingStorage");
        return meetingList;
    }

    public List<Meeting> findByOrganizerPersonId(int id){
        List<Meeting> meetingList = new ArrayList<>();
        for (Meeting meeting : meetingStorage){
            int organizerId = meeting.getOrganizer().getId();
            if (organizerId == id){
                meetingList.add(meeting);
            }
        }
        if (meetingList.isEmpty()) throw new RuntimeException("Organizers int id is not in the meetingStorage");
        return meetingList;
    }

    public List<Meeting> findByTopic(String topic){
        List<Meeting> meetingList = new ArrayList<>();
        for (Meeting meeting : meetingStorage){
            if (meeting.getTopic().equals(topic)){
                meetingList.add(meeting);
            }
        }
        if (meetingList.isEmpty()) throw new RuntimeException("String topic is not in the meetingStorage");
        return meetingList;
    }

    public int getMeetingCount(){
        return meetingStorage.size();
    }

    public Meeting persist(Meeting meeting){
        if(!meetingStorage.contains(meeting)){
            meetingStorage.add(meeting);
        }
        return meeting;
    }

    public boolean removeById(int id){
        for (Meeting meeting : meetingStorage){
            if (meeting.getId() == id){
                return meetingStorage.remove(meeting);
            }
        }
        return false;
    }
}
