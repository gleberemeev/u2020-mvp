package ru.ltst.u2020mvp.base;


import android.support.test.InstrumentationRegistry;

import ru.ltst.u2020mvp.TestU2020Application;

public abstract class BaseTest {
    protected TestU2020Application getApp() {
        return (TestU2020Application) InstrumentationRegistry.getTargetContext().getApplicationContext();
    }
}
