package com.hormiga6.androidapipractice;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class EnumTest {
    enum Day {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
        THURSDAY, FRIDAY, SATURDAY
    }

    @Test
    public void testToString(){
        assertThat(Day.SUNDAY.toString(),is("SUNDAY"));
    }

    @Test
    public void testValueOf(){
        assertThat(Day.valueOf("FRIDAY"), is(Day.FRIDAY));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValueOfWrongvalue(){
        Day.valueOf("hoge");
    }
}
