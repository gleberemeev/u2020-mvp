package ru.ltst.u2020mvp


class TestU2020Application : U2020App() {
    lateinit var testComponent: TestU2020Component
        private set

    override fun buildComponentAndInject() {
        testComponent = DaggerTestU2020Component.builder()
                .u2020AppModule(U2020AppModule(this))
                .build()
        testComponent.inject(this)
    }

    override fun component(): U2020Component {
        return testComponent
    }
}
