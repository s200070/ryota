package jp.ac.it_college.std.s20007.getupryota.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import jp.ac.it_college.std.s20007.getupryota.R
import jp.ac.it_college.std.s20007.getupryota.alarm
import jp.ac.it_college.std.s20007.getupryota.databinding.ActivityAlarmListViewBinding

class Alarm_List_View : AppCompatActivity() {
    lateinit var binding: ActivityAlarmListViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAlarmListViewBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val alarms =
            mutableListOf<alarm>(
                alarm("00:00","ryouta","平日",true),
                alarm("00:00","taiki","祝日",false),
                alarm("00:00","taiga","月火水木",true),
                alarm("00:00","eito","毎日",true),
            )

        val listAdapter = AlarmListAdapter(applicationContext)
        listAdapter.alarms = alarms

        val listView: ListView = findViewById(R.id.listview) as ListView
        listView.adapter = listAdapter

        binding.listview.setOnItemClickListener { parent, view, position, id ->

        }

    }
}