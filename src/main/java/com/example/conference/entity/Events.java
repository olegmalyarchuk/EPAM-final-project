package com.example.conference.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Events implements Serializable {
    private static final long serialVersionUID = 18292888943433707L;

    private int event_id;
    private String event_name_ua;
    private String event_name_en;
    private String event_place_ua;
    private String event_place_en;
    private String event_description_ua;
    private String event_description_en;
    private LocalDateTime event_date;
    private String event_photo_url;

    private int reportsCount;
    private int registeredCount;
    private int presentCount;
    private List<Reports> reports;

    public Events() {
        reports = new ArrayList<>();
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public String getEvent_name_ua() {
        return event_name_ua;
    }

    public void setEvent_name_ua(String event_name_ua) {
        this.event_name_ua = event_name_ua;
    }

    public String getEvent_name_en() {
        return event_name_en;
    }

    public void setEvent_name_en(String event_name_en) {
        this.event_name_en = event_name_en;
    }

    public String getEvent_place_ua() {
        return event_place_ua;
    }

    public void setEvent_place_ua(String event_place_ua) {
        this.event_place_ua = event_place_ua;
    }

    public String getEvent_place_en() {
        return event_place_en;
    }

    public void setEvent_place_en(String event_place_en) {
        this.event_place_en = event_place_en;
    }

    public String getEvent_description_ua() {
        return event_description_ua;
    }

    public void setEvent_description_ua(String event_description_ua) {
        this.event_description_ua = event_description_ua;
    }

    public String getEvent_description_en() {
        return event_description_en;
    }

    public void setEvent_description_en(String event_description_en) {
        this.event_description_en = event_description_en;
    }

    public LocalDateTime getEvent_date() {
        return event_date;
    }

    public void setEvent_date(LocalDateTime event_date) {
        this.event_date = event_date;
    }

    public String getEvent_photo_url() {
        return event_photo_url;
    }

    public void setEvent_photo_url(String event_photo_url) {
        this.event_photo_url = event_photo_url;
    }

    public boolean isFinished() {
        return event_date == null || LocalDateTime.now().isAfter(event_date);
    }

    public int getReportsCount() {
        return reportsCount;
    }

    public void setReportsCount(int reportsCount) {
        this.reportsCount = reportsCount;
    }

    public int getRegisteredCount() {
        return registeredCount;
    }

    public void setRegisteredCount(int registeredCount) {
        this.registeredCount = registeredCount;
    }

    public int getPresentCount() {
        return presentCount;
    }

    public void setPresentCount(int presentCount) {
        this.presentCount = presentCount;
    }

    public List<Reports> getReports() {
        return reports;
    }

    public void setReports(List<Reports> reports) {
       if(reports == null) {
           this.reportsCount = 0;
           this.reports = new ArrayList<>();
       } else {
           this.reports = reports;
           this.reportsCount = reports.size();
       }
    }

    @Override
    public String toString() {
        return "Events{" +
                "event_id=" + event_id +
                ", reportsCount=" + reportsCount +
                ", presentCount=" + presentCount +
                '}';
    }
}
