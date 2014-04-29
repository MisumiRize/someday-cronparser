package org.ayaseeli.someday.lib.cron;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class EntryTest {

    private Calendar now;

    @Before
    public void setUp() throws Exception {
        now = Calendar.getInstance();
        now.set(2014, 4, 26, 10, 24, 15);
    }

    @Test
    public void testNext() {
        Entry entry = Entry.parse("* * * * *", now);
        Calendar next = entry.next();
        assertEquals(next.get(Calendar.MINUTE), 25);
    }

    @Test
    public void testNext_willCarryHour() {
        Entry entry = Entry.parse("4,10 15 * * *", now);
        Calendar next = entry.next();
        assertEquals(next.get(Calendar.HOUR_OF_DAY), 15);
        assertEquals(next.get(Calendar.MINUTE), 4);
        Calendar next2 = entry.next();
        assertEquals(next2.get(Calendar.HOUR_OF_DAY), 15);
        assertEquals(next2.get(Calendar.MINUTE), 10);
        Calendar next3 = entry.next();
        assertEquals(next3.get(Calendar.DATE), 27);
        assertEquals(next3.get(Calendar.HOUR_OF_DAY), 15);
        assertEquals(next3.get(Calendar.MINUTE), 4);
    }
    
}
