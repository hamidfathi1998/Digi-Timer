package ir.hfathi.timer

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ir.hfathi.digitimer.TimerFactory
import ir.hfathi.digitimer.enums.TimerMode
import ir.hfathi.timer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        const val TIMER_DOWN_LIMIT_VALUE = 12_000L // 12 sec
        const val TIMER_UP_WITH_LIMIT_VALUE = 70_100L // 1 min, 10sec, 10 mill
        const val TIMER_DATE_FORMAT_VALUE = "mm : ss.SS" // 12 : 25.79
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        timerUpInit()
        timerDownInit()
        timerUpWithLimitInit()
    }

    private fun timerUpInit() {
        val mTimer = TimerFactory()
            .getInstance(TimerMode.TIMER_UP)
            .setDateFormatPattern(TIMER_DATE_FORMAT_VALUE)
            .setTapStopFinishInvoke(tapStopFinishInvoke = true)
            .timerTick { timerValue ->
                runOnUiThread {
                    binding.txtTimerUpValue.text = timerValue
                }
            }.finishTimer {
                runOnUiThread {
                    Toast.makeText(this, getString(R.string.finish_timer_up), Toast.LENGTH_SHORT)
                        .show()
                }
            }

        binding.btnTimerUpStart.setOnClickListener {
            mTimer.start()
        }

        binding.btnTimerUpStop.setOnClickListener {
            mTimer.stop()
        }

        binding.btnTimerUpPause.setOnClickListener {
            mTimer.pause()
        }

        binding.btnTimerUpResume.setOnClickListener {
            mTimer.resume()
        }

    }

    private fun timerDownInit() {
        val mTimer = TimerFactory()
            .getInstance(TimerMode.TIMER_DOWN)
            .setLimitToTimer(TIMER_DOWN_LIMIT_VALUE)
            .setDateFormatPattern(TIMER_DATE_FORMAT_VALUE)
            .timerTick { timerValue ->
                runOnUiThread {
                    binding.txtTimerDownValue.text = timerValue
                }
            }.finishTimer {
                runOnUiThread {
                    Toast.makeText(this, getString(R.string.finish_timer_down), Toast.LENGTH_SHORT)
                        .show()
                }
            }

        binding.btnTimerDownStart.setOnClickListener {
            mTimer.start()
        }

        binding.btnTimerDownStop.setOnClickListener {
            mTimer.stop()
        }

        binding.btnTimerDownPause.setOnClickListener {
            mTimer.pause()
        }

        binding.btnTimerDownResume.setOnClickListener {
            mTimer.resume()
        }
    }

    private fun timerUpWithLimitInit() {
        val mTimer = TimerFactory()
            .getInstance(TimerMode.TIMER_UP_WITH_LIMIT)
            .setLimitToTimer(TIMER_UP_WITH_LIMIT_VALUE)
            .setDateFormatPattern(TIMER_DATE_FORMAT_VALUE)
            .timerTick { timerValue ->
                runOnUiThread {
                    binding.txtTimerUpWithLimitValue.text = timerValue
                }
            }.finishTimer {
                runOnUiThread {
                    Toast.makeText(
                        this,
                        getString(R.string.finish_timer_up_with_limit),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        binding.btnTimerUpWithLimitStart.setOnClickListener {
            mTimer.start()
        }

        binding.btnTimerUpWithLimitStop.setOnClickListener {
            mTimer.stop()
        }

        binding.btnTimerUpWithLimitPause.setOnClickListener {
            mTimer.pause()
        }

        binding.btnTimerUpWithLimitResume.setOnClickListener {
            mTimer.resume()
        }
    }
}
