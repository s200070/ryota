package jp.ac.it_college.std.s20007.getupryota.list

import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.getSystemService
import jp.ac.it_college.std.s20007.getupryota.MainActivity
import jp.ac.it_college.std.s20007.getupryota.R
import jp.ac.it_college.std.s20007.getupryota.SettingAlarm
import jp.ac.it_college.std.s20007.getupryota.alarm

class ViewHolderItem(val timeText: TextView,
                     val nameText: TextView,
                     val formatText: TextView,
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
            val weekText = itemView.findViewById<TextView>(R.id.weekText)
            val onOff = itemView.findViewById<Switch>(R.id.OnOff)
            val format = itemView.findViewById<TextView>(R.id.formats)
            val repeet = itemView.findViewById<TextView>(R.id.repeat)
            val viewHolder = ViewHolderItem(timeText, nameText, format, weekText, onOff, repeet)
            itemView.tag = viewHolder
            viewHolder to itemView
        } else {
            convertView.tag as ViewHolderItem to convertView
        }
        val form = mapOf(0 to "英単語", 1 to "エクササイズ", 2 to "計算問題", 3 to "パズル", 4 to "なし")
        viewHolder.timeText.text = getItem(position)?.time
        viewHolder.nameText.text = getItem(position)?.name
        viewHolder.weekText.text = getItem(position)?.week
        viewHolder.formatText.text = form[getItem(position)?.format]
        viewHolder.repeat.text = if (getItem(position)?.repeat == true) {
            "ON"
        } else {
            "OFF"
        }
        viewHolder.switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(
                    context,
                    "アラーム:${getItem(position)?.name}オン", Toast.LENGTH_SHORT).show()


            } else {
                Toast.makeText(
                    context,
                    "アラーム:${getItem(position)?.name}オフ", Toast.LENGTH_SHORT).show()
            }
        }
        return view

    }

}
