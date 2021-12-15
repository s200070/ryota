package jp.ac.it_college.std.s20007.getupryota

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import jp.ac.it_college.std.s20007.getupryota.databinding.ActivityMainBinding
import jp.ac.it_college.std.s20007.getupryota.list.AlarmListAdapter

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.addButton.setOnClickListener{
            val intent = Intent(this, SettingAlarm::class.java)
            startActivity(intent)
        }


        val alarms =
            mutableListOf<alarm>(
                alarm("00:00","ryouta","平日",true, 0),
                alarm("00:00","taiki","祝日",false, 1),
                alarm("00:00","taiga","月火水木",true, 2),
                alarm("00:00","eito","毎日",true, 3),
            )

        val listAdapter = AlarmListAdapter(applicationContext)
        listAdapter.alarms = alarms

        val listView: ListView = findViewById(R.id.listview) as ListView
        listView.adapter = listAdapter

        binding.listview.setOnItemClickListener { parent, view, position, id ->

        }

    }
}