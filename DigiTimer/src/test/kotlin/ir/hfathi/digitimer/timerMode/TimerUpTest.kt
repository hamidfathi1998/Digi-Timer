package ir.hfathi.digitimer.timerMode

import ir.hfathi.digitimer.Constants.DEFAULT_DATE_FORMAT_PATTERN
import ir.hfathi.digitimer.Constants.MEDIUM_WAIT_TIME_BY_MILLISECOND
import ir.hfathi.digitimer.Constants.SHORT_WAIT_TIME_BY_MILLISECOND
import ir.hfathi.digitimer.TimerFactory
import ir.hfathi.digitimer.enums.TimerMode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
            .setDateFormatPattern(DEFAULT_DATE_FORMAT_PATTERN)
            .timerTick { timerValue ->
                mTimerValue = timerValue
            }
        mTimerUp.start()
        withContext(Dispatchers.IO) {
            Thread.sleep(SHORT_WAIT_TIME_BY_MILLISECOND)
        }
        mTimerUp.stop()

        mTimerValue = String()
        mTimerUp.start()
        withContext(Dispatchers.IO) {
            Thread.sleep(SHORT_WAIT_TIME_BY_MILLISECOND)
        }
        mTimerUp.stop()

        assert(mTimerValue.isNotEmpty())
    }

    @Test
    fun `is timer return value`()= runTest {
        var mTimerValue = String()
        val mTimerUp = TimerFactory()
            .getInstance(TimerMode.TIMER_UP)
            .setDateFormatPattern(DEFAULT_DATE_FORMAT_PATTERN)
            .timerTick { timerValue ->
                mTimerValue = timerValue
            }
        mTimerUp.start()
        withContext(Dispatchers.IO) {
            Thread.sleep(SHORT_WAIT_TIME_BY_MILLISECOND)
        }
        mTimerUp.stop()

        assert(mTimerValue.isNotEmpty())
    }

    @Test
    fun `is getTimerNowValue fun work correctly`() = runTest {
        var mTimerValue = String()
        val mTimerDown = TimerFactory()
            .getInstance(TimerMode.TIMER_UP)
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

}