package com.example.madd;

import com.example.madd.util.Utils;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit testing for Utility functions in the application
 * @Author - https://github.com/mdilshan
 */
public class UtilityUnitTest {
    @Test
    public void roundFloat_isCorrect() {
        Assert.assertThat(
                Utils.roundFloat(5.632f, 1),
                CoreMatchers.equalTo(5.6)
        );
    }

    @Test
    public void getAverage_isCorrect() {
        assertEquals(
                "get average value rounded to 1 decimal point",
                5.6,
                 Utils.getAverage(30,169),
                0
        );
    }

}
