package ru.ltst.u2020mvp

import ru.ltst.u2020mvp.ui.debug.DebugView

interface DebugInternalU2020Graph : InternalU2020Graph {
    fun inject(view: DebugView)
    @IsInstrumentationTest fun isInstrumentationTest() : Boolean
}
