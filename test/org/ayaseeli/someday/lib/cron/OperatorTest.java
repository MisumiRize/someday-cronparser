package org.ayaseeli.someday.lib.cron;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.SortedSet;

import org.junit.Test;

public class OperatorTest {

    @Test
    public void testGetCandidates_rangeOperator() {
        Operator operator = Operator.parse("4-10", 0);
        Set<Integer> candidates = operator.getCandidates(new Range(0, 23).toSortedSet());
        assertFalse(candidates.contains(3));
        assertTrue(candidates.contains(4));
        assertTrue(candidates.contains(10));
        assertFalse(candidates.contains(11));
    }

    @Test
    public void testGetCandidates_multipleOperator() {
        Operator operator = Operator.parse("10,20,30", 0);
        Set<Integer> candidates = operator.getCandidates(new Range(0, 23).toSortedSet());
        assertTrue(candidates.contains(10));
        assertTrue(candidates.contains(20));
        assertFalse(candidates.contains(30));
        assertFalse(candidates.contains(40));
    }

    @Test
    public void testGetCandidates_digitOperator() {
        Operator operator = Operator.parse("40", 0);
        assertTrue(operator.isMultiple());
        SortedSet<Integer> candidates = operator.getCandidates(new Range(0, 60).toSortedSet());
        assertEquals(candidates.size(), 1);
        assertEquals(candidates.first(), Integer.valueOf(40));
    }

    @Test
    public void testParse_willThrowException_intervalOperator() {
        Operator operator = Operator.parse("*", 0);
        assertTrue(operator instanceof IntervalOperator);
    }

}
