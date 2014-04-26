package org.ayaseeli.someday.lib.cron;

import java.util.Calendar;

class Entry {
    
    private MinuteField minuteField;
    private HourField hourField;
    
    Entry(Operator minute, Operator hour) {
        minuteField = new MinuteField(minute);
        hourField = new HourField(hour);
    }
    
}
