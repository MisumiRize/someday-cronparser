package org.ayaseeli.someday.lib.cron;

import java.util.Calendar;
import java.util.SortedSet;

class HourField extends Field {
    
    HourField(Operator operator) {
        super(operator);
    }
    
    SortedSet<Integer> getCandidates() {
        return getCandidates(new Range(0, 23).toSortedSet());
    }
    
    Calendar postpone(Calendar now) {
        Calendar next = (Calendar) now.clone();
        next.set(Calendar.MINUTE, 0);
        SortedSet<Integer> candidates = getCandidates();
        do {
            next.add(Calendar.HOUR_OF_DAY, 1);
        } while (!candidates.contains(next.get(Calendar.HOUR_OF_DAY)));
        return next;
    }
    
    boolean isSatisfiedWith(Calendar next) {
        return getCandidates().contains(next.get(Calendar.HOUR_OF_DAY));
    }
    
}
