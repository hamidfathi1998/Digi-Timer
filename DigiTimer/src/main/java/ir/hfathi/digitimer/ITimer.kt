package ir.hfathi.digitimer

interface ITimer {

    fun initTimer()

    fun start() : ITimer

    fun stop() : ITimer

    fun resume() : ITimer

    fun pause() : ITimer

    fun timerTick(callback :(String) -> Unit) : ITimer

    fun finishTimer(callback :() -> Unit) : ITimer

    fun getTimerNowValue() : String

    fun destroyTimer()

    fun setLimitToTimer(limitValue:Long) : ITimer

    fun setDateFormatPattern(dateFormatPattern:String) : ITimer

    fun setTapStopFinishInvoke(tapStopFinishInvoke:Boolean):ITimer
}