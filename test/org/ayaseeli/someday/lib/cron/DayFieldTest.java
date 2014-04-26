package org.ayaseeli.someday.lib.cron;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class DayFieldTest {

    private Calendar now;
    
    @Before
    public void setUp() throws Exception {
        now = Calendar.getInstance();
        now.set(2014, 3, 26, 10, 24, 15);
    }

    @Test
    public void testPostpone_willIncreaseMonth_whenLastOfMonth() {
        DayField field = new DayField(new Operator("31"));
        Calendar postponed = field.postpone(now);
        assertEquals(postponed.get(Calendar.MONTH), 4);
        assertEquals(postponed.get(Calendar.DATE), 31);
    }

}
