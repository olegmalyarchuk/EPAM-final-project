package com.example.conference.comparators;

import com.example.conference.entity.Events;
import com.example.conference.exceptions.DBException;

import java.util.Comparator;

/**
 * Date comparator for comparing events
 *
 */
public class DateComparator implements Comparator<Events> {
    @Override
    public int compare(Events a, Events b) {
        return a.getEvent_date().compareTo(b.getEvent_date());
    }
}
