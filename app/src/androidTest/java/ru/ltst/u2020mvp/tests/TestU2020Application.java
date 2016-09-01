package ru.ltst.u2020mvp.tests;


import ru.ltst.u2020mvp.U2020App;
import ru.ltst.u2020mvp.U2020AppModule;
import ru.ltst.u2020mvp.U2020Component;

public class TestU2020Application extends U2020App {
    private TestU2020Component component;

    @Override
    public void buildComponentAndInject() {
        component = DaggerTestU2020Component.builder()
                .u2020AppModule(new U2020AppModule(this))
                .build();
        component.inject(this);
    }

    @Override
    public U2020Component component() {
        return component;
    }

    public TestU2020Component getTestComponent() {
        return component;
    }
}
