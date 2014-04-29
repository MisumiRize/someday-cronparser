package org.ayaseeli.someday.lib.cron;

import java.util.SortedSet;
import java.util.TreeSet;

class Operator {

    protected String operator;

    protected Operator(String operator) {
        this.operator = operator;
    }

    static Operator parse(String operator, int startAt) {
        if (operator.matches("^[0-9]+(,[0-9]+)*$")) {
            return new Operator(operator);
        }
        if (operator.matches("^[0-9]+-[0-9]+$")) {
            return new Operator(operator);
        }
        return IntervalOperator.parse(operator, startAt);
    }

    boolean isRange() {
        return operator.contains("-");
    }

    boolean isMultiple() {
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
