package ru.ltst.u2020mvp

import com.f2prateek.rx.preferences.Preference
import ru.ltst.u2020mvp.data.NetworkDelay
import ru.ltst.u2020mvp.ui.debug.DebugView

interface DebugInternalU2020Graph : InternalU2020Graph {
    fun inject(view: DebugView)
    @IsInstrumentationTest fun isInstrumentationTest() : Boolean
    @NetworkDelay fun networkDelay() : Preference<Long>
}
