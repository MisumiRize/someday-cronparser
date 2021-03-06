package org.ayaseeli.someday.lib.cron;

import static org.junit.Assert.*;

import java.util.SortedSet;

import org.junit.Test;

public class IntervalOperatorTest {

    @Test
    public void testEveryOperator() {
        IntervalOperator operator = IntervalOperator.parse("*", 5);
        assertEquals(operator.getInterval(), 1);
    }

    @Test
    public void testCalculateInterval_returnsDivisor_divisionOperator() {
        IntervalOperator operator = IntervalOperator.parse("*/3", 5);
        assertEquals(operator.getInterval(), 3);
    }

    @Test
    public void testGetCandidates_everyOperator() {
        IntervalOperator operator = IntervalOperator.parse("*", 5);
        SortedSet<Integer> candidates = operator.getCandidates(new Range(0, 23).toSortedSet());
        assertFalse(candidates.contains(-1));
        assertTrue(candidates.contains(0));
        assertTrue(candidates.contains(23));
        assertFalse(candidates.contains(24));
    }

    @Test
    public void testGetCandidates_divisionOperator() {
        IntervalOperator operator = IntervalOperator.parse("*/3", 5);
        SortedSet<Integer> candidates = operator.getCandidates(new Range(0, 23).toSortedSet());
        assertFalse(candidates.contains(-1));
        assertTrue(candidates.contains(2));
        assertTrue(candidates.contains(5));
        assertTrue(candidates.contains(23));
        assertFalse(candidates.contains(26));
    }

    @Test(expected = IllegalEntryException.class)
    public void testParse_willThrowException_digitOperator() {
        IntervalOperator.parse("12", 0);
    }

}
