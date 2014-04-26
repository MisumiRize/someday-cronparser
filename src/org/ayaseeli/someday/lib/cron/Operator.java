package org.ayaseeli.someday.lib.cron;

import java.util.SortedSet;
import java.util.TreeSet;

class Operator {
    
    protected String operator;
    
    public Operator(String operator) {
        this.operator = operator;
    }
    
    public boolean isRange() {
        return operator.contains("-");
    }
    
    public boolean isMultiple() {
        return operator.contains(",") || operator.matches("^[0-9]+$");
    }
    
    SortedSet<Integer> getCandidates(SortedSet<Integer> mother) {
        SortedSet<Integer> candidates = getCandidates();
        candidates.retainAll(mother);
        return candidates;
    }
    
    private SortedSet<Integer> getCandidates() {
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
    
    private SortedSet<Integer> getRange() {
        String[] pieces = operator.split("-");
        if (pieces.length != 2) {
            throw new IllegalEntryException();
        }
        return new Range(Integer.parseInt(pieces[0]), Integer.parseInt(pieces[1])).toSortedSet();
    }
    
}
