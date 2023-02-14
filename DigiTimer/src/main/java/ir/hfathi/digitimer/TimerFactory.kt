package ir.hfathi.digitimer

import ir.hfathi.digitimer.enums.TimerMode
import ir.hfathi.digitimer.timerMode.TimerDown
import ir.hfathi.digitimer.timerMode.TimerUp
import ir.hfathi.digitimer.timerMode.TimerUpWithLimit

class TimerFactory {

    fun getInstance(timerMode: TimerMode): ITimer =
        when (timerMode) {
            TimerMode.TIMER_UP -> TimerUp()
            TimerMode.TIMER_DOWN -> TimerDown()
            TimerMode.TIMER_UP_WITH_LIMIT -> TimerUpWithLimit()
        }
}