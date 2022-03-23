package com.example.conference.comparators;

import com.example.conference.entity.Events;

import java.util.Comparator;


public class ReportsCountComparator implements Comparator<Events> {
    @Override
    public int compare(Events a, Events b) {
        return a.getReportsCount()-b.getReportsCount();
    }
}
