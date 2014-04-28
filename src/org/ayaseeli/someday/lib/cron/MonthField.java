package org.ayaseeli.someday.lib.cron;

import java.util.Calendar;
import java.util.SortedSet;

class MonthField extends Field {

    MonthField(Operator operator) {
        super(operator);
    }

    SortedSet<Integer> getCandidates() {
        return operator.getCandidates(new Range(1, 12).toSortedSet());
    }

    Calendar postpone(Calendar now) {
        Calendar next = (Calendar) now.clone();
        next.set(Calendar.DATE, 1);
        SortedSet<Integer> candidates = getCandidates();
        do {
            next.add(Calendar.MONTH, 1);
        } while (!candidates.contains(next.get(Calendar.MONTH) + 1));
        return next;
    }

    boolean isSatisfiedWith(Calendar next) {
        return getCandidates().contains(next.get(Calendar.MONTH) + 1);
    }

}
