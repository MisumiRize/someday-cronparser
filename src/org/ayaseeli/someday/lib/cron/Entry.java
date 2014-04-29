package org.ayaseeli.someday.lib.cron;

import java.util.Calendar;

class Entry {

    private MinuteField minuteField;
    private HourField hourField;
    private DayField dayField;
    private MonthField monthField;
    private WeekField weekField;
    private Calendar now;

    private Entry(Operator minute, Operator hour, Operator day, Operator month, Operator week, Calendar now) {
        minuteField = new MinuteField(minute);
        hourField = new HourField(hour);
        dayField = new DayField(day);
        monthField = new MonthField(month);
        weekField = new WeekField(week);
        this.now = now;
    }

    static Entry parse(String entry) {
        return parse(entry, Calendar.getInstance());
    }

    static Entry parse(String entry, Calendar now) {
        String[] operators = entry.split(" ");
        if (operators.length != 5) {
            throw new IllegalEntryException();
        }
        Calendar startAt = (Calendar) now.clone();
        startAt.add(Calendar.MINUTE, 1);
        startAt.set(Calendar.SECOND, 0);
        startAt.set(Calendar.MILLISECOND, 0);
        return new Entry(Operator.parse(operators[0], startAt.get(Calendar.MINUTE)),
                Operator.parse(operators[1], startAt.get(Calendar.HOUR_OF_DAY)),
                Operator.parse(operators[2], startAt.get(Calendar.DATE)),
                Operator.parse(operators[3], startAt.get(Calendar.MONTH) + 1),
                Operator.parse(operators[4], startAt.get(Calendar.DAY_OF_WEEK)), now);
    }

    Calendar next() {
        now = next(now);
        return (Calendar) now.clone();
    }

    private Calendar next(Calendar now) {
        Calendar next = (Calendar) now.clone();
        next.add(Calendar.MINUTE, 1);
        next.set(Calendar.SECOND, 0);
        next.set(Calendar.MILLISECOND, 0);
        while (true) {
            if (!monthField.isSatisfiedWith(next)) {
                next = monthField.postpone(next);
                continue;
            }
            if (!weekField.isSatisfiedWith(next)) {
                next = weekField.postpone(next);
                continue;
            }
            if (!dayField.isSatisfiedWith(next)) {
                next = dayField.postpone(next);
                continue;
            }
            if (!hourField.isSatisfiedWith(next)) {
                next = hourField.postpone(next);
                continue;
            }
            if (!minuteField.isSatisfiedWith(next)) {
                next = minuteField.postpone(next);
                continue;
            }
            return next;
        }
    }

}
