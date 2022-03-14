package com.example.conference.entity;

import java.io.Serializable;

public class Report_speakers implements Serializable {
    private static final long serialVersionUID = 222926644813433707L;
    private int id;
    private int report_id;
    private int speaker_id;
    private User speaker;

    public Report_speakers(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

    public int getSpeaker_id() {
        return speaker_id;
    }

    public void setSpeaker_id(int speaker_id) {
        this.speaker_id = speaker_id;
    }

    public User getSpeaker() {
        return speaker;
    }

    public void setSpeaker(User speaker) {
       if(speaker != null) {
           setSpeaker_id(speaker.getId());
       }
        this.speaker = speaker;
    }

    @Override
    public String toString() {
        return "Report_speakers{" +
                "id=" + id +
                ", report_id=" + report_id +
                ", speaker_id=" + speaker_id +
                ", speaker=" + speaker +
                '}';
    }
}
