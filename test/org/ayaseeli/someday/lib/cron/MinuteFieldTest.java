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
        now.set(2014, 4, 26, 11, 24, 15);
    }

    @Test
    public void testPostpone() {
        MinuteField field = new MinuteField(new Operator("*"));
        Calendar postponed = field.postpone(now);
        assertNotEquals(postponed, now);
        assertEquals(postponed.get(Calendar.MINUTE), 25);
        assertEquals(postponed.get(Calendar.SECOND), 0);
        assertEquals(postponed.get(Calendar.MILLISECOND), 0);
    }

}
