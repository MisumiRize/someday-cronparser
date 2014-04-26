package org.ayaseeli.someday.lib.cron;

import java.util.Calendar;
import java.util.SortedSet;
import java.util.TreeSet;

class Refrain {
    
    private SortedSet<Calendar> startAt = new TreeSet<Calendar>();
    private long interval;
    
    public Refrain(Calendar startAt, long interval) {
        this.startAt.add(startAt);
        this.interval = interval;
    }
    
    public long getInterval() {
        return interval;
    }
    
    public long getIntervalInMillis() {
        return interval * 1000;
    }

}
