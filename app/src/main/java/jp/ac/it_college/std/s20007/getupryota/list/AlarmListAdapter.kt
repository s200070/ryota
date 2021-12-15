package jp.ac.it_college.std.s20007.getupryota.list

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import jp.ac.it_college.std.s20007.getupryota.AlarmView
import jp.ac.it_college.std.s20007.getupryota.alarm

class AlarmListAdapter(private val context: Context) : BaseAdapter(){
    var alarms: List<alarm> = emptyList()

    override fun getCount(): Int = alarms.size

    override fun getItem(position: Int): Any? = alarms[position]

    override fun getItemId(position: Int): Long = 0

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View =
        ((convertView as? AlarmView) ?: AlarmView(context)).apply {
            setAlarm(alarms[position])
        }


}