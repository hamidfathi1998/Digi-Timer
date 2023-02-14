package ir.hfathi.digitimer.timerMode

import ir.hfathi.digitimer.TimerFactory
import ir.hfathi.digitimer.enums.TimerMode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TimerDownTest {

    @Test
    fun `if timer is cancel and touch start to work timer again working correctly`() = runTest {
        var mTimerValue: String
        val mTimerDown = TimerFactory()
            .getInstance(TimerMode.TIMER_DOWN)
            .setLimitToTimer(1_000)
            .setDateFormatPattern("mm : ss : SS")
            .timerTick { timerValue ->
                mTimerValue = timerValue
            }
        mTimerDown.start()
        withContext(Dispatchers.IO) {
            Thread.sleep(1_000)
        }
        mTimerDown.stop()

        mTimerValue = String()
        mTimerDown.start()
        withContext(Dispatchers.IO) {
            Thread.sleep(1_000)
        }
        mTimerDown.stop()

        assert(mTimerValue.isNotEmpty())
    }

    @Test
    fun `is timer return value`() = runTest {
        var mTimerValue = String()
        val mTimerDown = TimerFactory()
            .getInstance(TimerMode.TIMER_DOWN)
            .setLimitToTimer(1_000)
            .setDateFormatPattern("mm : ss : SS")
            .timerTick { timerValue ->
                mTimerValue = timerValue
            }
        mTimerDown.start()
        withContext(Dispatchers.IO) {
            Thread.sleep(1_000)
        }
        mTimerDown.stop()

        assert(mTimerValue.isNotEmpty())
    }

    @Test
    fun `is timer finish in the limit time`() = runTest {
        var mTimerValue = String()
        val mTimerDown = TimerFactory()
            .getInstance(TimerMode.TIMER_DOWN)
            .setLimitToTimer(8_000)
            .setDateFormatPattern("mm:ss.SS")
            .timerTick { timerValue ->
                mTimerValue = timerValue
            }
        mTimerDown.start()
        withContext(Dispatchers.IO) {
            Thread.sleep(8_000)
        }
        assert(mTimerValue == "00:00.00")
    }


    @Test
    fun `set zero to timer limit`() = runTest {
        var mTimerValue = String()
        val mTimerDown = TimerFactory()
            .getInstance(TimerMode.TIMER_DOWN)
            .setLimitToTimer(0)
            .setDateFormatPattern("mm:ss.SS")
            .timerTick { timerValue ->
                mTimerValue = timerValue
            }
        mTimerDown.start()
        withContext(Dispatchers.IO) {
            Thread.sleep(8_000)
        }
        assert(mTimerValue == "00:00.00")
    }

}