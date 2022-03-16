package com.example.conference.entity;

import java.io.Serializable;
import java.lang.annotation.Target;

public class Reports implements Serializable {
    private static final long serialVersionUID = 182926643433707L;

    private int report_id;
    private int event_id;
    private String report_name_ua;
    private String report_name_en;

    private Report_speakers report_speaker;

    public Reports() {

    }

    public Reports(int report_id) {
        this.report_id = report_id;
    }

    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
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

    public Report_speakers getReport_speaker() {
        return report_speaker;
    }

    public void setReport_speaker(Report_speakers report_speaker) {
        if(report_speaker != null) {
            report_speaker.setReport_id(getReport_id());
        }
        this.report_speaker = report_speaker;
    }

    @Override
    public String toString() {
        return "Reports{" +
                "report_id=" + report_id +
                ", event_id=" + event_id +
                ", report_name_ua='" + report_name_ua + '\'' +
                ", report_name_en='" + report_name_en + '\'' +
                ", report_speaker=" + report_speaker +
                '}';
    }
}
