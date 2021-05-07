package org.example.sequencers;

public class MeetingSequencer {
    static int meetingID;

    public static int nextMeetingId(){
        return meetingID = meetingID + 1;
    }

    public static void reset(){
        meetingID = 0;
    }
}
