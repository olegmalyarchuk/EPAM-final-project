package com.example.conference.comparators;

import com.example.conference.entity.Events;

import java.util.Comparator;

public class DateComparator implements Comparator<Events> {
    @Override
    public int compare(Events a, Events b) {
        return a.getEvent_date().compareTo(b.getEvent_date());
    }
}
