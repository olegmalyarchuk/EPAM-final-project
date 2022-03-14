package com.example.conference.entity;

import java.io.Serializable;

public class Event_users implements Serializable {
    private static final long serialVersionUID = 382926644813433707L;

    private int id;
    private int user_id;
    private int event_id;
    private boolean present;
    private User user;

    public Event_users() {
    }

    public Event_users(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Event_users{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", event_id=" + event_id +
                ", present=" + present +
                ", user=" + user +
                '}';
    }
}
