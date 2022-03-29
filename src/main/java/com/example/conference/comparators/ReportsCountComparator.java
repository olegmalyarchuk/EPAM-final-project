package com.example.conference.comparators;

import com.example.conference.entity.Events;

import java.util.Comparator;

/**
 * Reports count comparator for comparing events
 *
 */
public class ReportsCountComparator implements Comparator<Events> {
    @Override
    public int compare(Events a, Events b) {
        return a.getReportsCount()-b.getReportsCount();
    }
}
