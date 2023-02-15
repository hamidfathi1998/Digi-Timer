package ir.hfathi.digitimer.timerMode

import ir.hfathi.digitimer.Constants.TIMER_CYCLE_DELAY_VALUE
import ir.hfathi.digitimer.Constants.TIMER_CYCLE_PERIOD_VALUE
import ir.hfathi.digitimer.convertMilToTimerFormat
import ir.hfathi.digitimer.getTime
import ir.hfathi.digitimer.getZeroWithTimerFormat
import java.util.*
import java.util.Timer

class TimerUpWithLimit: TimerMode() {

    override fun initTimer() {
        if (finishTimerLimit) {
            timer = Timer()
        }
        timer.schedule(object : TimerTask() {
            override fun run() {
                if (running) {
                    val inTime = getTime() - firstTime
                    mNowTimerValue = convertMilToTimerFormat(dateTimeFormat =mDateFormatPattern,millisecond =inTime)
                    if (inTime >= mLimitValue) {
                        timer.cancel()
                        mNowTimerValue = convertMilToTimerFormat(dateTimeFormat = mDateFormatPattern, millisecond = mLimitValue)
                        mCallback?.invoke(mNowTimerValue)
                        mFinishTimerTick?.invoke()
                    }else {
                        mCallback?.invoke(mNowTimerValue)
                    }
                }
            }
        }, TIMER_CYCLE_DELAY_VALUE, TIMER_CYCLE_PERIOD_VALUE)
    }
}