package ru.ltst.u2020mvp.data;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.threeten.bp.Instant;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class InstantAdapterTest {
    @Test
    public void toJson() throws Exception {
        Instant input = Instant.now();
        String expected = input.toString();
        assertEquals(expected, new InstantAdapter().toJson(input));
    }

    @Test
    public void fromJson() throws Exception {
        Instant expected = Instant.now();
        String input = expected.toString();
        assertEquals(expected, new InstantAdapter().fromJson(input));
    }
}