package ir.hfathi.digitimer.timerMode

import ir.hfathi.digitimer.Constants
import ir.hfathi.digitimer.ITimer
import ir.hfathi.digitimer.getTime
import java.util.*

open class TimerMode : ITimer {

    protected var timer = Timer()
    protected var firstTime = 0L
    protected var mLimitValue: Long = 0
    protected var finishTimerLimit = false
    protected var running: Boolean = false
    protected var mNowTimerValue = String()
    protected var mCallback: ((String) -> Unit)? = null
    protected var mFinishTimerTick: (() -> Unit)? = null
    protected var mDateFormatPattern: String = Constants.DEFAULT_DATE_FORMAT_PATTERN

    private var breakTime = 0L
    private var isPauseTimer = false
    private var mTapStopFinishInvoke = false

    override fun initTimer() = Unit

    override fun start(): ITimer {
        isPauseTimer = false
        firstTime = getTime()
        running = true
        initTimer()
        return this
    }

    override fun stop(): ITimer {
        if (mTapStopFinishInvoke) {
            mFinishTimerTick?.invoke()
        }
        isPauseTimer = false
        running = false
        try {
            timer.cancel()
        } catch (ex: java.lang.Exception) {
            ex.printStackTrace()
        }
        finishTimerLimit = true
        return this
    }

    override fun resume(): ITimer {
        if (isPauseTimer) {
            isPauseTimer = false
            val allBreakTime = getTime() - breakTime
            firstTime += allBreakTime
            running = true
        }
        return this
    }

    override fun pause(): ITimer {
        isPauseTimer = true
        breakTime = getTime()
        running = false
        return this
    }

    override fun timerTick(callback: (String) -> Unit): ITimer {
        mCallback = callback
        return this
    }

    override fun finishTimer(callback: () -> Unit): ITimer {
        mFinishTimerTick = callback
        return this
    }

    override fun getTimerNowValue(): String = mNowTimerValue

    override fun destroyTimer() {
        try {
            timer.cancel()
            timer.purge()
        } catch (ex: java.lang.Exception) {
            ex.printStackTrace()
        }
    }

    override fun setLimitToTimer(limitValue: Long): ITimer {
        mLimitValue = limitValue
        return this
    }

    override fun setDateFormatPattern(dateFormatPattern: String): ITimer {
        mDateFormatPattern = dateFormatPattern
        return this
    }

    override fun setTapStopFinishInvoke(tapStopFinishInvoke: Boolean): ITimer {
        mTapStopFinishInvoke = tapStopFinishInvoke
        return this
    }
}