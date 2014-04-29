package org.ayaseeli.someday.lib.cron;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class HourFieldTest {

    private Calendar now;

    @Before
    public void setUp() throws Exception {
        now = Calendar.getInstance();
        now.set(2014, 4, 26, 10, 24, 15);
    }

    @Test
    public void testPostpone_whenIntervalOperator() {
        HourField field = new HourField(IntervalOperator.parse("*/3", now.get(Calendar.HOUR)));
        Calendar postponed = field.postpone(now);
        assertEquals(postponed.get(Calendar.HOUR_OF_DAY), 13);
        assertEquals(postponed.get(Calendar.MINUTE), 0);
        Calendar postponedAgain = field.postpone(postponed);
        assertEquals(postponedAgain.get(Calendar.HOUR_OF_DAY), 16);
    }

    @Test
    public void testPostpone_increasesDay_whenMultipleOperatorNotContains() {
        HourField field = new HourField(new Operator("0"));
        Calendar postponed = field.postpone(now);
        assertEquals(postponed.get(Calendar.DATE), 27);
        assertEquals(postponed.get(Calendar.HOUR_OF_DAY), 0);
    }

}
