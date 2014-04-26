package org.ayaseeli.someday.lib.cron;

import java.util.SortedSet;
import java.util.TreeSet;

class Range {
    
    private int from;
    private int to;
    
    Range(int from, int to) {
        this.from = from;
        this.to = to;
    }
    
    SortedSet<Integer> toSortedSet() {
        SortedSet<Integer> range = new TreeSet<Integer>();
        for (int i = from; i <= to; i++) {
            range.add(i);
        }
        return range;
    }

}
