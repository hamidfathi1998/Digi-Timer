package ir.hfathi.digitimer.timerMode

import ir.hfathi.digitimer.TimerFactory
import ir.hfathi.digitimer.enums.TimerMode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.Test
import kotlin.test.assertFails

@OptIn(ExperimentalCoroutinesApi::class)
class TimerUpWithLimitTest {

    @Test
    fun `if timer is cancel and touch start to work timer again working correctly`() = runTest {
        var mTimerValue: String
        val mTimerUpWithLimit = TimerFactory()
            .getInstance(TimerMode.TIMER_UP_WITH_LIMIT)
            .setLimitToTimer(8_000)
            .setDateFormatPattern("mm : ss : SS")
            .timerTick { timerValue ->
                mTimerValue = timerValue
            }
        mTimerUpWithLimit.start()
        withContext(Dispatchers.IO) {
            Thread.sleep(1_000)
        }
        mTimerUpWithLimit.stop()

        mTimerValue = String()
        mTimerUpWithLimit.start()
        withContext(Dispatchers.IO) {
            Thread.sleep(1_000)
        }
        mTimerUpWithLimit.stop()

        assert(mTimerValue.isNotEmpty())
    }

    @Test
    fun `is timer return value`()= runTest {
        var mTimerValue = String()
        val mTimerUpWithLimit = TimerFactory()
            .getInstance(TimerMode.TIMER_UP_WITH_LIMIT)
            .setLimitToTimer(8_000)
            .setDateFormatPattern("mm : ss : SS")
            .timerTick { timerValue ->
                mTimerValue = timerValue
            }
        mTimerUpWithLimit.start()
        withContext(Dispatchers.IO) {
            Thread.sleep(1_000)
        }
        mTimerUpWithLimit.stop()

        assert(mTimerValue.isNotEmpty())
    }

    @Test
    fun `is timer finish in the limit time`()= runTest {
        var mTimerValue = String()
        val mTimerUpWithLimit = TimerFactory()
            .getInstance(TimerMode.TIMER_UP_WITH_LIMIT)
            .setLimitToTimer(8_000)
            .setDateFormatPattern("mm:ss.SS")
            .timerTick { timerValue ->
                mTimerValue = timerValue
            }
        mTimerUpWithLimit.start()
        withContext(Dispatchers.IO) {
            Thread.sleep(8_000)
        }
        println(mTimerValue)
        assert(mTimerValue == "00:08.00")
    }

    @Test
    fun `set zero to timer limit`()= runTest {
        var mTimerValue = String()
        val mTimerUpWithLimit = TimerFactory()
            .getInstance(TimerMode.TIMER_UP_WITH_LIMIT)
            .setLimitToTimer(0)
            .setDateFormatPattern("mm:ss.SS")
            .timerTick { timerValue ->
                mTimerValue = timerValue
            }
        mTimerUpWithLimit.start()
        withContext(Dispatchers.IO) {
            Thread.sleep(0)
        }
        println(mTimerValue)
        assert(mTimerValue == "00:00.00")
    }

}