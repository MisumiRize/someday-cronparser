package org.ayaseeli.someday.lib.cron;

import java.util.Calendar;
import java.util.SortedSet;

class WeekField extends Field {

    WeekField(Operator operator) {
        super(operator);
    }
    
    SortedSet<Integer> getCandidates() {
        return getCandidates(new Range(0, 7).toSortedSet());
    }
    
    Calendar postpone(Calendar now) {
        Calendar next = (Calendar) now.clone();
        next.set(Calendar.HOUR_OF_DAY, 0);
        do {
            next.add(Calendar.DATE, 1);
        } while (!isFulfilled(next));
        return next;
    }
    
    private boolean isFulfilled(Calendar next) {
        SortedSet<Integer> candidates = getCandidates();
        if (candidates.contains(next.get(Calendar.DAY_OF_WEEK) - 1)) {
            return true;
        }
        if (candidates.contains(7) && next.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return true;
        }
        return false;
    }

}
