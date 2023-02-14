package ir.hfathi.digitimer

interface ITimer {

//    val limitValue: Long?
//    val dateFormatPattern : String
//        get() = DEFAULT_DATE_FORMAT_PATTERN

    fun initTimer()

    fun start() : ITimer

    fun stop() : ITimer

    fun resume() : ITimer

    fun pause() : ITimer

    fun timerTick(callback :(String) -> Unit) : ITimer

    fun setLimitToTimer(limitValue:Long) : ITimer

    fun setDateFormatPattern(dateFormatPattern:String) : ITimer
}