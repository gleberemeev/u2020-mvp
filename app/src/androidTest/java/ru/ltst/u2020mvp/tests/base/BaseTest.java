package ru.ltst.u2020mvp.tests.base;


import android.support.test.InstrumentationRegistry;

import ru.ltst.u2020mvp.tests.TestU2020Application;

public abstract class BaseTest {
    protected TestU2020Application getApp() {
        return (TestU2020Application) InstrumentationRegistry.getInstrumentation().getTargetContext();
    }
}
