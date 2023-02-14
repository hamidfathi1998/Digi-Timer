package ir.hfathi.digitimer.timerMode

import ir.hfathi.digitimer.Constants
import ir.hfathi.digitimer.ITimer
import ir.hfathi.digitimer.getTime
import java.util.*

open class TimerMode : ITimer {

    protected var timer = Timer()
    protected var firstTime = 0L
    private var breakTime = 0L
    protected var mLimitValue: Long = 0
    protected var isPauseTimer = false
    protected var finishTimerLimit = false
    protected var running: Boolean = false
    protected var mCallback: ((String) -> Unit)? = null
    protected var mDateFormatPattern: String = Constants.DEFAULT_DATE_FORMAT_PATTERN

    override fun initTimer() = Unit

    override fun start()  : ITimer{
        isPauseTimer = false
        firstTime = getTime()
        running = true
        initTimer()
        return this
    }

    override fun stop() : ITimer {
        isPauseTimer = false
        running = false
        timer.cancel()
        finishTimerLimit = true
        return this
    }

    override fun resume()  : ITimer{
        if (isPauseTimer) {
            isPauseTimer = false
            val allBreakTime = getTime() - breakTime
            firstTime += allBreakTime
            running = true
        }
        return this
    }

    override fun pause()  : ITimer{
        isPauseTimer = true
        breakTime = getTime()
        running = false
        return this
    }

    override fun timerTick(callback: (String) -> Unit) : ITimer {
        mCallback = callback
        return this
    }

    override fun setLimitToTimer(limitValue: Long): ITimer {
        mLimitValue = limitValue
        return this
    }
    override fun setDateFormatPattern(dateFormatPattern: String): ITimer {
        mDateFormatPattern = dateFormatPattern
        return this
    }
}