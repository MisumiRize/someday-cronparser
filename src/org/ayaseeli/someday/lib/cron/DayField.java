package org.ayaseeli.someday.lib.cron;

import java.util.Calendar;
import java.util.SortedSet;

class DayField extends Field {

    DayField(Operator operator) {
        super(operator);
    }
    
    SortedSet<Integer> getCandidates() {
        return operator.getCandidates(new Range(1, 31).toSortedSet());
    }
    
    Calendar postpone(Calendar now) {
        Calendar next = (Calendar) now.clone();
        next.set(Calendar.HOUR_OF_DAY, 0);
        SortedSet<Integer> candidates = getCandidates();
        do {
            next.add(Calendar.DATE, 1);
        } while (!candidates.contains(next.get(Calendar.DATE)));
        return next;
    }
    
    boolean isSatisfiedWith(Calendar next) {
        return getCandidates().contains(next.get(Calendar.DATE));
    }

}
