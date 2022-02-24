package jp.ac.it_college.std.s20007.getupryota.list

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import jp.ac.it_college.std.s20007.getupryota.R
import jp.ac.it_college.std.s20007.getupryota.alarm
import jp.ac.it_college.std.s20007.getupryota.format.startActivity
import org.w3c.dom.Text
import java.util.*
import kotlin.math.min


class ViewHolderItem(val timeText: TextView,
                     val nameText: TextView,
                     val formatText: TextView,
                     val soundText: TextView,
                     val weekText: TextView,
                     val switch: Switch,
                     val repeat: TextView
                     )

class AlarmListAdapter(context: Context, val list: List<alarm>) : ArrayAdapter<alarm>(context, 0, list) {
    private val layoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val (viewHolder, view) = if (convertView == null) {
            //初回
            val itemView = layoutInflater.inflate(R.layout.activity_alarm_view, parent, false)
            val timeText = itemView.findViewById<TextView>(R.id.time_text)
            val nameText = itemView.findViewById<TextView>(R.id.name_text)
            val soundText = itemView.findViewById<TextView>(R.id.sound_text)
            val weekText = itemView.findViewById<TextView>(R.id.weekText)
            val onOff = itemView.findViewById<Switch>(R.id.OnOff)
            val format = itemView.findViewById<TextView>(R.id.formats)
            val repeet = itemView.findViewById<TextView>(R.id.repeat)
            val viewHolder = ViewHolderItem(timeText, nameText, format, soundText, weekText, onOff, repeet)
            itemView.tag = viewHolder
            viewHolder to itemView
        } else {
            convertView.tag as ViewHolderItem to convertView
        }
        val form = mapOf(0 to "英単語", 1 to "おもろクイズ", 2 to "計算問題", 3 to "なし")
        viewHolder.timeText.text = getItem(position)?.time
        viewHolder.nameText.text = getItem(position)?.name
        viewHolder.weekText.text = getItem(position)?.week
        viewHolder.soundText.text = getItem(position)?.sound
        viewHolder.formatText.text = form[getItem(position)?.format]
        viewHolder.repeat.text = if (getItem(position)?.repeat == true) {
            "ON"
        } else {
            "OFF"
        }

        val alarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager
        val time = getItem(position)?.time?.split(":")
        val hour = time?.get(0)
        val minute = time?.get(1)
        println(time)
        val calendar = Calendar.getInstance().apply {
            if (hour != null && minute != null) {
                set(Calendar.HOUR_OF_DAY, hour.toInt())
                set(Calendar.MINUTE, minute.toInt())
                if (before(Calendar.getInstance())) {
                    add(Calendar.DATE, 1)
                }
            }
        }


        val intent = Intent(context, startActivity::class.java)
        intent.putExtra("FORM", getItem(position)?.format)
        intent.putExtra("SOUND", getItem(position)?.sound)
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        viewHolder.switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(
                    context,
                    "アラーム:${getItem(position)?.name}オン", Toast.LENGTH_SHORT).show()

                alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent
                )


            } else {
                Toast.makeText(
                    context,
                    "アラーム:${getItem(position)?.name}オフ", Toast.LENGTH_SHORT).show()

                alarmManager.cancel(pendingIntent)

            }
        }
        return view

    }

}
