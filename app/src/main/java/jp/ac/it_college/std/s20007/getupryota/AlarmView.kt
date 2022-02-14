package jp.ac.it_college.std.s20007.getupryota

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView

class AlarmView: FrameLayout {
    constructor(context: Context): super(context)

    var time_text: TextView? = null
    var name_text: TextView? = null
    var mase_text: TextView? = null
    var repeat: TextView? = null
    var format_text: TextView? = null


    init {
        LayoutInflater.from(context).inflate(R.layout.activity_alarm_view, this)
        time_text = findViewById(R.id.time_text) as TextView
        name_text = findViewById(R.id.name_text) as TextView
        mase_text = findViewById(R.id.weekText) as TextView
        repeat = findViewById(R.id.repeat) as TextView
        format_text = findViewById(R.id.formats) as TextView

    }

    fun setAlarm(alarm: alarm) {
        val form = mapOf(0 to "英単語", 1 to "エクササイズ", 2 to "計算問題", 3 to "パズル", 4 to "なし")
        if (alarm.repeat) { repeat?.text = "ON"}else{repeat?.text = "OFF"}
        time_text?.text = alarm.time
        name_text?.text = alarm.name
        mase_text?.text = alarm.week
        format_text?.text = form[alarm.format]
    }

}