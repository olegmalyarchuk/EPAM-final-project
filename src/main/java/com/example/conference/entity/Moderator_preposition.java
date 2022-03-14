package com.example.conference.entity;

import java.io.Serializable;

public class Moderator_preposition implements Serializable {
    private static final long serialVersionUID = 182926643433707L;

    private int id;
    private int report_id;
    private int speaker_id;

    public Moderator_preposition(int id) {
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

    @Override
    public String toString() {
        return "Moderator_preposition{" +
                "id=" + id +
                ", report_id=" + report_id +
                ", speaker_id=" + speaker_id +
                '}';
    }
}
