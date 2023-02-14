package ir.hfathi.digitimer.timerMode

import ir.hfathi.digitimer.TimerFactory
import ir.hfathi.digitimer.enums.TimerMode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TimerUpTest {

    @Test
    fun `if timer is cancel and touch start to work timer again working correctly`() = runTest {
        var mTimerValue: String
        val mTimerUp = TimerFactory()
            .getInstance(TimerMode.TIMER_UP)
            .setDateFormatPattern("mm : ss : SS")
            .timerTick { timerValue ->
                mTimerValue = timerValue
            }
        mTimerUp.start()
        withContext(Dispatchers.IO) {
            Thread.sleep(1_000)
        }
        mTimerUp.stop()

        mTimerValue = String()
        mTimerUp.start()
        withContext(Dispatchers.IO) {
            Thread.sleep(1_000)
        }
        mTimerUp.stop()

        assert(mTimerValue.isNotEmpty())
    }

    @Test
    fun `is timer return value`()= runTest {
        var mTimerValue = String()
        val mTimerUp = TimerFactory()
            .getInstance(TimerMode.TIMER_UP)
            .setDateFormatPattern("mm : ss : SS")
            .timerTick { timerValue ->
                mTimerValue = timerValue
            }
        mTimerUp.start()
        withContext(Dispatchers.IO) {
            Thread.sleep(1_000)
        }
        mTimerUp.stop()

        assert(mTimerValue.isNotEmpty())
    }

}