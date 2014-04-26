package org.ayaseeli.someday.lib.cron;

import java.util.SortedSet;
import java.util.TreeSet;

class IntervalOperator extends Operator {
    
    private int startAt;
    
    IntervalOperator(String operator, int startAt) {
        super(operator);
        this.startAt = startAt;
    }
    
    int getInterval() {
        if (!hasDivisor()) {
            return 1;
        }
        String[] pieces = operator.split("/");
        if (pieces.length != 2) {
            throw new IllegalEntryException();
        }
        int parsed = Integer.parseInt(pieces[1]);
        if (parsed == 0) {
            throw new IllegalEntryException();
        }
        return parsed;
    }
    
    @Override
    SortedSet<Integer> getCandidates(SortedSet<Integer> mother) {
        int first = mother.first();
        int last = mother.last();
        int interval = getInterval();
        int startAt = this.startAt;
        SortedSet<Integer> candidates = new TreeSet<Integer>();
        while (startAt - interval > first) {
            startAt -= interval;
        } 
        while (startAt <= last) {
            candidates.add(startAt);
            startAt += interval;
        }
        return candidates;
    }
    
    private boolean hasDivisor() {
        return operator.contains("/");
    }

}
