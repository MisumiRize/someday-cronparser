package org.ayaseeli.someday.lib.cron;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

public class OperatorTest {

    @Test
    public void testEveryOperator() {
        Operator operator = new Operator("*");
        assertTrue(operator.hasInterval());
        assertEquals(operator.getInterval(), 1);
    }
    
    @Test
    public void testHasValidInterval_ReturnsFalse_RangeOperator() {
        Operator operator = new Operator("4-10");
        assertFalse(operator.hasInterval());
    }
    
    @Test
    public void testGetRange_RangeOperator() {
        Operator operator = new Operator("4-10");
        Set<Integer> range = operator.getRange();
        assertFalse(range.contains(3));
        assertTrue(range.contains(4));
        assertTrue(range.contains(10));
        assertFalse(range.contains(11));
    }
    
    @Test
    public void testHasValidInterval_ReturnsFalse_MultipleOperator() {
        Operator operator = new Operator("2,3");
        assertFalse(operator.hasInterval());
    }
    
    @Test
    public void testCalculateInterval_ReturnsDivisor_DivisionOperator() {
        Operator operator = new Operator("*/3");
        assertEquals(operator.getInterval(), 3);
    }

}
