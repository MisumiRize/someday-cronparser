package org.ayaseeli.someday.lib.cron;

import java.util.SortedSet;
import java.util.TreeSet;

class IntervalOperator extends Operator {

    private int startAt;

    private IntervalOperator(String operator, int startAt) {
        super(operator);
        this.startAt = startAt;
    }

    static IntervalOperator parse(String operator, int startAt) {
        if (!operator.matches("^\\*(\\/[0-9]+)?$")) {
            throw new IllegalEntryException();
        }
        return new IntervalOperator(operator, startAt);
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
        SortedSet<Integer> candidates = new TreeSet<Integer>();
        int interval = getInterval();
        for (int candidate : mother) {
            if ((candidate - startAt) % interval == 0) {
                candidates.add(candidate);
            }
        }
        return candidates;
    }

    private boolean hasDivisor() {
        return operator.contains("/");
    }

}
