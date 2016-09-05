package ru.ltst.u2020mvp.util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PreconditionsTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void checkNotNull() throws Exception {
        Integer ref = 1;
        Preconditions.checkNotNull(ref);
        expectedException.expect(NullPointerException.class);
        Preconditions.checkNotNull(null);
    }

    @Test
    public void checkNotNull1() throws Exception {
        Integer ref = 1;
        Preconditions.checkNotNull(ref);
        expectedException.expectMessage("message");
        Preconditions.checkNotNull(null, "message");
    }

}