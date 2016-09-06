package ru.ltst.u2020mvp;

import dagger.Component;
import ru.ltst.u2020mvp.base.navigation.activity.ActivityScreenSwitcherTest;
import ru.ltst.u2020mvp.data.DebugDataModule;
import ru.ltst.u2020mvp.ui.DebugUiModule;

@ApplicationScope
@Component(modules = {U2020AppModule.class, DebugUiModule.class, DebugDataModule.class, TestU2020Module.class})
public interface TestU2020Component extends U2020Component {
    void inject(ActivityScreenSwitcherTest activityScreenSwitcherTest);
}
