package jp.ac.it_college.std.s20007.getupryota

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.Switch
import android.widget.TextView
import org.w3c.dom.Text

class AlarmView: FrameLayout {
    constructor(context: Context): super(context)

    var time_text: TextView? = null
    var name_text: TextView? = null
    var mase_text: TextView? = null
    var on_off: Switch? = null
    var format_text: TextView? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.activity_alarm_view, this)
        time_text = findViewById(R.id.time_text) as TextView
        name_text = findViewById(R.id.name_text) as TextView
        mase_text = findViewById(R.id.MaseText) as TextView
        on_off = findViewById(R.id.OnOff) as Switch
        format_text = findViewById(R.id.formats) as TextView
    }

    fun setArarm(alarm: alarm) {
        val form = mapOf(0 to "英単語", 1 to "エクササイズ", 2 to "計算問題", 3 to "パズル", 4 to "なし")
        time_text?.text = alarm.time
        name_text?.text = alarm.name
        mase_text?.text = alarm.messege
        on_off?.isChecked = alarm.onoff
        format_text?.text = form[alarm.format]
    }

}