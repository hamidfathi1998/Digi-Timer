package ir.hfathi.digitimer.timerMode

import ir.hfathi.digitimer.Constants.DEFAULT_DATE_FORMAT_PATTERN
import ir.hfathi.digitimer.Constants.LONG_WAIT_TIME_BY_MILLISECOND
import ir.hfathi.digitimer.Constants.MEDIUM_WAIT_TIME_BY_MILLISECOND
import ir.hfathi.digitimer.Constants.SHORT_WAIT_TIME_BY_MILLISECOND
import ir.hfathi.digitimer.Constants.ZERO_DATE_FORMAT
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
            .setLimitToTimer(SHORT_WAIT_TIME_BY_MILLISECOND)
            .setDateFormatPattern(DEFAULT_DATE_FORMAT_PATTERN)
            .timerTick { timerValue ->
                mTimerValue = timerValue
            }
        mTimerDown.start()
        withContext(Dispatchers.IO) {
            Thread.sleep(SHORT_WAIT_TIME_BY_MILLISECOND)
        }
        mTimerDown.stop()

        mTimerValue = String()
        mTimerDown.start()
        withContext(Dispatchers.IO) {
            Thread.sleep(SHORT_WAIT_TIME_BY_MILLISECOND)
        }
        mTimerDown.stop()

        assert(mTimerValue.isNotEmpty())
    }

    @Test
    fun `is timer return value`() = runTest {
        var mTimerValue = String()
        val mTimerDown = TimerFactory()
            .getInstance(TimerMode.TIMER_DOWN)
            .setLimitToTimer(SHORT_WAIT_TIME_BY_MILLISECOND)
            .setDateFormatPattern(DEFAULT_DATE_FORMAT_PATTERN)
            .timerTick { timerValue ->
                mTimerValue = timerValue
            }
        mTimerDown.start()
        withContext(Dispatchers.IO) {
            Thread.sleep(SHORT_WAIT_TIME_BY_MILLISECOND)
        }
        mTimerDown.stop()

        assert(mTimerValue.isNotEmpty())
    }

    @Test
    fun `is timer finish in the limit time`() = runTest {
        var mTimerValue = String()
        val mTimerDown = TimerFactory()
            .getInstance(TimerMode.TIMER_DOWN)
            .setLimitToTimer(LONG_WAIT_TIME_BY_MILLISECOND)
            .setDateFormatPattern(DEFAULT_DATE_FORMAT_PATTERN)
            .timerTick { timerValue ->
                mTimerValue = timerValue
            }
        mTimerDown.start()
        withContext(Dispatchers.IO) {
            Thread.sleep(LONG_WAIT_TIME_BY_MILLISECOND)
        }
        assert(mTimerValue == ZERO_DATE_FORMAT)
    }

    @Test
    fun `is getTimerNowValue fun work correctly`() = runTest {
        var mTimerValue = String()
        val mTimerDown = TimerFactory()
            .getInstance(TimerMode.TIMER_DOWN)
            .setLimitToTimer(LONG_WAIT_TIME_BY_MILLISECOND)
            .setDateFormatPattern(DEFAULT_DATE_FORMAT_PATTERN)
            .timerTick { timerValue ->
                mTimerValue = timerValue
            }
        mTimerDown.start()
        withContext(Dispatchers.IO) {
            Thread.sleep(MEDIUM_WAIT_TIME_BY_MILLISECOND)
        }
        mTimerDown.stop()
        val mTimerNowValue = mTimerDown.getTimerNowValue()
        assert(mTimerValue == mTimerNowValue)
    }

    @Test
    fun `set zero to timer limit`() = runTest {
        var mTimerValue = String()
        val mTimerDown = TimerFactory()
            .getInstance(TimerMode.TIMER_DOWN)
            .setLimitToTimer(0)
            .setDateFormatPattern(DEFAULT_DATE_FORMAT_PATTERN)
            .timerTick { timerValue ->
                mTimerValue = timerValue
            }
        mTimerDown.start()
        withContext(Dispatchers.IO) {
            Thread.sleep(LONG_WAIT_TIME_BY_MILLISECOND)
        }
        assert(mTimerValue == ZERO_DATE_FORMAT)
    }

}