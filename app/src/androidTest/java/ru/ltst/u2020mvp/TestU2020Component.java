package ru.ltst.u2020mvp;

import dagger.Component;
import ru.ltst.u2020mvp.data.DebugDataModule;
import ru.ltst.u2020mvp.ui.DebugUiModule;
import ru.ltst.u2020mvp.ui.ExternalIntentActivityTest;
import ru.ltst.u2020mvp.ui.screen.main.MainActivityTest;

@ApplicationScope
@Component(modules = {U2020AppModule.class, DebugUiModule.class, DebugDataModule.class, TestU2020Module.class})
public interface TestU2020Component extends U2020Component {
    void inject(MainActivityTest mainActivityTest);

    void inject(ExternalIntentActivityTest externalIntentActivityTest);
}
