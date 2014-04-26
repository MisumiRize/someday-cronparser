package org.ayaseeli.someday.lib.cron;

import java.util.Calendar;

class Entry {
    
    private MinuteField minuteField;
    
    Entry(Operator minute) {
        minuteField = new MinuteField(minute);
    }
    
    public Refrain refrain(Calendar now) {
        Calendar postponed = minuteField.postpone(now);
        return new Refrain(postponed, minuteField.getIntervalInSeconds());
    }
    
}
