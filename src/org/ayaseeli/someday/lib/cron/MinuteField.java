package org.ayaseeli.someday.lib.cron;

import java.util.Calendar;
import java.util.SortedSet;

class MinuteField extends Field {
    
    MinuteField(Operator operator) {
        super(operator);
    }
    
    SortedSet<Integer> getCandidates() {
        return getCandidates(new Range(0, 59).toSortedSet());
    }
    
    Calendar postpone(Calendar now) {
        Calendar next = (Calendar) now.clone();
        next.set(Calendar.SECOND, 0);
        next.set(Calendar.MILLISECOND, 0);
        SortedSet<Integer> candidates = getCandidates();
        do {
            next.add(Calendar.MINUTE, 1);
        } while (!candidates.contains(next.get(Calendar.MINUTE)));
        return next;
    }
    
    boolean isSatisfiedWith(Calendar next) {
        return getCandidates().contains(next.get(Calendar.MINUTE));
    }
    
}
