package jp.ac.it_college.std.s20007.getupryota

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.Switch
import android.widget.TextView

class AlarmView: FrameLayout {
    constructor(context: Context): super(context)

    var time_text: TextView? = null
    var name_text: TextView? = null
    var mase_text: TextView? = null
    var on_off: Switch? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.activity_alarm_view, this)
        time_text = findViewById(R.id.time_text) as TextView
        name_text = findViewById(R.id.name_text) as TextView
        mase_text = findViewById(R.id.MaseText) as TextView
        on_off = findViewById(R.id.OnOff) as Switch
    }

    fun setArarm(alarm: alarm) {
        time_text?.text = alarm.time
        name_text?.text = alarm.name
        mase_text?.text = alarm.messege
        on_off?.isChecked = alarm.onoff
    }

}