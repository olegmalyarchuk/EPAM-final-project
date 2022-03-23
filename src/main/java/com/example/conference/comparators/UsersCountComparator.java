package com.example.conference.comparators;

import com.example.conference.entity.Events;

import java.util.Comparator;

public class UsersCountComparator implements Comparator<Events> {
    @Override
    public int compare(Events a, Events b) {
        return a.getRegisteredCount()-b.getRegisteredCount();
    }
}
