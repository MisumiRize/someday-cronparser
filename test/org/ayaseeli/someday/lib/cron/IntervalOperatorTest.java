package org.ayaseeli.someday.lib.cron;

import static org.junit.Assert.*;

import java.util.SortedSet;

import org.junit.Test;

public class IntervalOperatorTest {

    @Test
    public void testEveryOperator() {
        IntervalOperator operator = new IntervalOperator("*", 5);
        assertEquals(operator.getInterval(), 1);
    }
    
    @Test
    public void testCalculateInterval_ReturnsDivisor_DivisionOperator() {
        IntervalOperator operator = new IntervalOperator("*/3", 5);
        assertEquals(operator.getInterval(), 3);
    }
    
    @Test
    public void testGetCandidates_whenDivisionOperator() {
        IntervalOperator operator = new IntervalOperator("*/3", 5);
        SortedSet<Integer> mother = new Range(0, 23).toSortedSet();
        SortedSet<Integer> candidates = operator.getCandidates(mother);
        assertFalse(candidates.contains(-1));
        assertTrue(candidates.contains(2));
        assertTrue(candidates.contains(5));
        assertTrue(candidates.contains(23));
        assertFalse(candidates.contains(26));
    }
    
}
