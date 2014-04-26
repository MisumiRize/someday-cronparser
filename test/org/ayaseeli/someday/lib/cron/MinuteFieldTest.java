package org.ayaseeli.someday.lib.cron;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.ayaseeli.someday.lib.cron.MinuteField;
import org.ayaseeli.someday.lib.cron.Operator;
import org.junit.Before;
import org.junit.Test;

public class MinuteFieldTest {
    
    private Calendar now;
    
    @Before
    public void setUp() throws Exception {
        now = Calendar.getInstance();
        now.set(2014, 4, 26, 10, 24, 15);
    }

    @Test
    public void testPostpone_whenEveryOperator() {
        MinuteField field = new MinuteField(new IntervalOperator("*", now.get(Calendar.MINUTE)));
        Calendar postponed = field.postpone(now);
        assertNotEquals(postponed, now);
        assertEquals(postponed.get(Calendar.MINUTE), 25);
        assertEquals(postponed.get(Calendar.SECOND), 0);
        assertEquals(postponed.get(Calendar.MILLISECOND), 0);
    }
    
    @Test
    public void testPostpone_increasesHour_whenMultipleOperatorNotContains() {
        MinuteField field = new MinuteField(new Operator("10,15"));
        Calendar postponed = field.postpone(now);
        assertEquals(postponed.get(Calendar.HOUR), 11);
        assertEquals(postponed.get(Calendar.MINUTE), 10);
    }
    
    @Test(expected = IllegalEntryException.class)
    public void testPostpone_whenRangeOperatorContainsInvalidMinute() {
        MinuteField field = new MinuteField(new Operator("80,100"));
        field.postpone(now);
    }
    
    @Test
    public void testPostpone_whenIntervalOperator() {
        MinuteField field = new MinuteField(new IntervalOperator("*/3", now.get(Calendar.MINUTE)));
        Calendar postponed = field.postpone(now);
        assertEquals(postponed.get(Calendar.HOUR), 10);
        assertEquals(postponed.get(Calendar.MINUTE), 27);
        Calendar postponedAgain = field.postpone(postponed);
        assertEquals(postponedAgain.get(Calendar.MINUTE), 30);
    }

}
