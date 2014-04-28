package org.ayaseeli.someday.lib.cron;

import java.util.SortedSet;

abstract class Field {

    protected Operator operator;
    protected SortedSet<Integer> candidates;

    Field(Operator operator) {
        this.operator = operator;
    }

    abstract SortedSet<Integer> getCandidates();

    protected SortedSet<Integer> getCandidates(SortedSet<Integer> mother) {
        if (candidates == null) {
            candidates = operator.getCandidates(mother);
        }
        if (candidates.size() == 0) {
            throw new IllegalEntryException();
        }
        return candidates;
    }

}
