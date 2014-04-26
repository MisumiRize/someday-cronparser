package org.ayaseeli.someday.lib.cron;

import java.util.Calendar;
import java.util.Set;
import java.util.TreeSet;

class MinuteField {
    
    private Set<Integer> mother = new TreeSet<Integer>();
    private Operator operator;
    
    MinuteField(Operator operator) {
        this.operator = operator;
    }
    
    public Calendar postpone(Calendar now) {
        Calendar next = (Calendar) now.clone();
        if (operator.hasInterval()) {
            next.add(Calendar.MINUTE, (int) operator.getInterval());
            next.set(Calendar.SECOND, 0);
            next.set(Calendar.MILLISECOND, 0);
            return next;
        }
        return null;
    }
    
    public long getIntervalInSeconds() {
        return operator.getInterval() * 60;
    }
    
    protected Set<Integer> name() {
        Set<Integer> parsed = operator.getCandidates();
        if (!mother.containsAll(parsed)) {
            throw new IllegalEntryException();
        }
        return parsed;
    }

}
