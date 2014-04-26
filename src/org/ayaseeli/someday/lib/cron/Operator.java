package org.ayaseeli.someday.lib.cron;

import java.util.SortedSet;
import java.util.TreeSet;

class Operator {
    
    private String operator;
    
    public Operator(String operator) {
        this.operator = operator;
    }
    
    public boolean hasInterval() {
        return !isRange() && !isMultiple();
    }
    
    public long getInterval() {
        if (!hasDivisor()) {
            return 1;
        }
        String[] pieces = operator.split("/");
        if (pieces.length != 2) {
            throw new IllegalEntryException();
        }
        long parsed = Long.parseLong(pieces[1]);
        if (parsed == 0) {
            throw new IllegalEntryException();
        }
        return parsed;
    }
    
    public boolean isRange() {
        return operator.contains("-");
    }
    
    public SortedSet<Integer> getRange() {
        String[] pieces = operator.split("-");
        if (pieces.length != 2) {
            throw new IllegalEntryException();
        }
        SortedSet<Integer> range = new TreeSet<Integer>();
        int from = Integer.parseInt(pieces[0]);
        int to = Integer.parseInt(pieces[1]);
        for (int i = from; i <= to; i++) {
            range.add(i);
        }
        return range;
    }
    
    public boolean isMultiple() {
        return operator.contains(",");
    }
    
    public SortedSet<Integer> getCandidates() {
        if (isRange()) {
            return getRange();
        }
        SortedSet<Integer> candidates = new TreeSet<Integer>();
        String[] pieces = operator.split(",");
        for (String piece : pieces) {
            candidates.add(Integer.valueOf(piece));
        }
        return candidates;
    }
    
    private boolean hasDivisor() {
        return operator.contains("/");
    }
    
}
