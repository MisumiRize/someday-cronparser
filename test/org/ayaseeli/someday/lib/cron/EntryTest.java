package org.ayaseeli.someday.lib.cron;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class EntryTest {

    private Calendar now;
    private Operator every;

    @Before
    public void setUp() throws Exception {
        now = Calendar.getInstance();
        now.set(2014, 4, 26, 10, 24, 15);
        every = IntervalOperator.parse("*", 1);
    }

    @Test
    public void testNext() {
        Entry entry = new Entry(now, every, every, every, every, every);
        Calendar next = entry.next();
        assertEquals(next.get(Calendar.MINUTE), 25);
    }

    @Test
    public void testNext_willCarryHour() {
        Operator operator = new Operator("15");
        Entry entry = new Entry(now, every, operator, every, every, every);
        Calendar next = entry.next();
        assertEquals(next.get(Calendar.HOUR_OF_DAY), 15);
        assertEquals(next.get(Calendar.MINUTE), 0);
    }

}
