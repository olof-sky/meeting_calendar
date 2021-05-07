package org.example.models;

import org.example.sequencers.MeetingSequencer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

public class Meeting {
    private int id;
    private String topic;
    private LocalDate meetingDate;
    private LocalTime start;
    private LocalTime end;
    private Person organizer;
    private List<Person> attendants;

    public Meeting(int id, String topic, LocalDate meetingDate, LocalTime start, LocalTime end, Person organizer){
        this.id = id;
        this.topic = topic;
        this.meetingDate = meetingDate;
        this.start = start;
        this.end = end;
        this.organizer = organizer;
    }

    public Meeting (String topic, LocalDate meetingDate, LocalTime start, LocalTime end, Person organizer){
        this(MeetingSequencer.nextMeetingId(), topic, meetingDate, start, end, organizer); }

    public void addAttendant(Person attendant){
        attendants.add(attendant);
    }

    public void setAttendants(List<Person> attendants) {
        this.attendants = attendants;
    }

    public List<Person> getAttendants(){
        return attendants;
    }

    public int getId() {
        return id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        if (topic == null || topic.isEmpty()) throw new RuntimeException("String topic was null");
        this.topic = topic;
    }

    public LocalDate getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(LocalDate meetingDate) {
        if (meetingDate == null) throw new RuntimeException("LocalDate meetingDate was null");
        this.meetingDate = meetingDate;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        if (start == null) throw new RuntimeException("LocalTime start was null");
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        if (end == null) throw new RuntimeException("LocalTime end was null");
        this.end = end;
    }

    public void setOrganizer(Person organizer) {
        if (organizer == null) throw new RuntimeException("Person organizer was null");
        this.organizer = organizer;
    }

    public Person getOrganizer() {
        return organizer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return id == meeting.id && topic.equals(meeting.topic) && meetingDate.equals(meeting.meetingDate) && start.equals(meeting.start) && end.equals(meeting.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, topic, meetingDate, start, end);
    }
}
