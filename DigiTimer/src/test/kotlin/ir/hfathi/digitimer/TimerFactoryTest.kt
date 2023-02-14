package ir.hfathi.digitimer

import ir.hfathi.digitimer.enums.TimerMode
import ir.hfathi.digitimer.timerMode.TimerDown
import ir.hfathi.digitimer.timerMode.TimerUp
import ir.hfathi.digitimer.timerMode.TimerUpWithLimit
import org.junit.Test

class TimerFactoryTest {

    @Test
    fun `Ensure set timerUp type to timer factory and get back timerUp class`(){
        val mTimerUp = TimerFactory().getInstance(TimerMode.TIMER_UP)
        assert(mTimerUp is TimerUp)
    }

    @Test
    fun `Ensure set TimerUpWithLimit type to timer factory and get back TimerUpWithLimit class`(){
        val mTimerUpWithLimit = TimerFactory().getInstance(TimerMode.TIMER_UP_WITH_LIMIT)
        assert(mTimerUpWithLimit is TimerUpWithLimit)
    }

    @Test
    fun `Ensure set TimerDown type to timer factory and get back TimerDown class`(){
        val mTimerDown = TimerFactory().getInstance(TimerMode.TIMER_DOWN)
        assert(mTimerDown is TimerDown)
    }
}