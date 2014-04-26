package org.ayaseeli.someday.lib.cron;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class WeekFieldTest {
    
    Calendar now;

    @Before
    public void setUp() throws Exception {
        now = Calendar.getInstance();
        now.set(2014, 3, 26, 10, 24, 15);
    }

    @Test
    public void testPostpone_willReturnSunday_whenCandidateIs0() {
        WeekField field = new WeekField(new Operator("0"));
        Calendar postponed = field.postpone(now);
        assertEquals(postponed.get(Calendar.DATE), 27);
        assertEquals(postponed.get(Calendar.DAY_OF_WEEK), Calendar.SUNDAY);
    }

    @Test
    public void testPostpone_willReturnSunday_whenCandidateIs7() {
        WeekField field = new WeekField(new Operator("7"));
        Calendar postponed = field.postpone(now);
        assertEquals(postponed.get(Calendar.DATE), 27);
        assertEquals(postponed.get(Calendar.DAY_OF_WEEK), Calendar.SUNDAY);
    }

}
