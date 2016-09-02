package ru.ltst.u2020mvp.tests;

import dagger.Component;
import ru.ltst.u2020mvp.ApplicationScope;
import ru.ltst.u2020mvp.U2020AppModule;
import ru.ltst.u2020mvp.U2020Component;
import ru.ltst.u2020mvp.base.navigation.activity.ActivityScreenSwitcherTest;
import ru.ltst.u2020mvp.data.DebugDataModule;
import ru.ltst.u2020mvp.tests.ui.GalleryActivityTest;
import ru.ltst.u2020mvp.ui.DebugUiModule;
import ru.ltst.u2020mvp.util.EnumPreferencesTest;

@ApplicationScope
@Component(modules = {U2020AppModule.class, DebugUiModule.class, DebugDataModule.class, TestU2020Module.class})
public interface TestU2020Component extends U2020Component {
    void inject(EnumPreferencesTest test);

    void inject(ActivityScreenSwitcherTest activityScreenSwitcherTest);
}
