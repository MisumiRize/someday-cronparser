package org.ayaseeli.someday.lib.cron;

import java.util.Calendar;

class Entry {
    
    private Calendar now;
    private MinuteField minuteField;
    private HourField hourField;
    private DayField dayField;
    private MonthField monthField;
    private WeekField weekField;
    
    Entry(Calendar now, Operator minute, Operator hour, Operator day, Operator month, Operator week) {
        this.now = now;
        minuteField = new MinuteField(minute);
        hourField = new HourField(hour);
        dayField = new DayField(day);
        monthField = new MonthField(month);
        weekField = new WeekField(week);
    }
    
    Calendar next() {
        now.add(Calendar.MINUTE, 1);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);
        while (true) {
            if (!monthField.isSatisfiedWith(now)) {
                now = monthField.postpone(now);
                continue;
            }
            if (!weekField.isSatisfiedWith(now)) {
                now = weekField.postpone(now);
                continue;
            }
            if (!dayField.isSatisfiedWith(now)) {
                now = dayField.postpone(now);
                continue;
            }
            if (!hourField.isSatisfiedWith(now)) {
                now = hourField.postpone(now);
                continue;
            }
            if (!minuteField.isSatisfiedWith(now)) {
                now = minuteField.postpone(now);
                continue;
            }
            return now;
        }
    }
    
}
