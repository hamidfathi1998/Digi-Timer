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
                    if (finalTimeMill <= 0) {
                        timer.cancel()
                        mCallback?.invoke(getZeroWithTimerFormat(mDateFormatPattern))
                    }else {
                        mCallback?.invoke(
                            convertMilToTimerFormat(
                                mDateFormatPattern,
                                finalTimeMill
                            )
                        )
                    }
                }
            }
        }, TIMER_CYCLE_DELAY_VALUE, TIMER_CYCLE_PERIOD_VALUE)
    }

}