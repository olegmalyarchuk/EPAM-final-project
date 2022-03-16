package com.example.conference.entity;

import java.io.Serializable;

public class Speaker_preposition implements Serializable {
    private static final long serialVersionUID = 329266813433707L;

    private int id;
    private int report_id;
    private int speaker_id;
    private User speaker;

    public Speaker_preposition() {
    }

    public Speaker_preposition(int id) {
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
        this.speaker = speaker;
    }

    @Override
    public String toString() {
        return "Speaker_preposition{" +
                "id=" + id +
                ", report_id=" + report_id +
                ", speaker_id=" + speaker_id +
                ", speaker=" + speaker +
                '}';
    }
}
