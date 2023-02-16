package ir.hfathi.digitimer.timerMode

import ir.hfathi.digitimer.Constants.TIMER_CYCLE_DELAY_VALUE
import ir.hfathi.digitimer.Constants.TIMER_CYCLE_PERIOD_VALUE
import ir.hfathi.digitimer.convertMilToTimerFormat
import ir.hfathi.digitimer.getTime
import ir.hfathi.digitimer.getZeroWithTimerFormat
import java.util.*
import java.util.Timer

class TimerDown: TimerMode() {

    private var finalTimeMill = 0L
    override fun initTimer() {
        if (finishTimerLimit) {
            timer = Timer()
        }
        timer.schedule(object : TimerTask() {
            override fun run() {
                if (running) {
                    val inTime = getTime() - firstTime
                    finalTimeMill = mLimitValue  - inTime
                    mNowTimerValue = convertMilToTimerFormat(
                        mDateFormatPattern,
                        finalTimeMill
                    )
                    if (finalTimeMill <= 0) {
                        cancelTimer()
                        mNowTimerValue = getZeroWithTimerFormat(mDateFormatPattern)
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