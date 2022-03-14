package com.example.conference.entity;

import java.io.Serializable;

public class Report_preposition implements Serializable {
    private static final long serialVersionUID = 282926644813433707L;
    private int id;
    private int event_id;
    private int speaker_id;
    private String report_name_ua;
    private String report_name_en;

    public Report_preposition() {

    }

    public Report_preposition(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public int getSpeaker_id() {
        return speaker_id;
    }

    public void setSpeaker_id(int speaker_id) {
        this.speaker_id = speaker_id;
    }

    public String getReport_name_ua() {
        return report_name_ua;
    }

    public void setReport_name_ua(String report_name_ua) {
        this.report_name_ua = report_name_ua;
    }

    public String getReport_name_en() {
        return report_name_en;
    }

    public void setReport_name_en(String report_name_en) {
        this.report_name_en = report_name_en;
    }

    @Override
    public String toString() {
        return "Report_preposition{" +
                "id=" + id +
                ", event_id=" + event_id +
                ", speaker_id=" + speaker_id +
                ", report_name_ua='" + report_name_ua + '\'' +
                ", report_name_en='" + report_name_en + '\'' +
                '}';
    }
}
