package jp.ac.it_college.std.s20007.getupryota

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import androidx.activity.result.contract.ActivityResultContracts
import jp.ac.it_college.std.s20007.getupryota.databinding.ActivityMainBinding
import jp.ac.it_college.std.s20007.getupryota.list.AlarmListAdapter

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val alarms =
        mutableListOf<alarm>(
            alarm("00:00", "ryouta", "平日", true, 0),
            alarm("00:00", "taiki", "祝日", false, 1),
            alarm("00:00", "taiga", "月火水木", true, 2),
            alarm("00:00", "eito", "毎日", true, 3),
        )
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.addButton.setOnClickListener {
            val intent = Intent(this, SettingAlarm::class.java)
            getName.launch(intent)
        }

        setAlarm()

        binding.listview.setOnItemClickListener { parent, view, position, id ->

        }
    }

    private val getName =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val time = it.data?.getStringExtra("TIME")
                val name = it.data?.getStringExtra("NAME")
                val sound = it.data?.getStringExtra("SOUND")
                val format = it.data?.getIntExtra("FORMAT", 0)
                val week = it.data?.getStringExtra("WEEK")
                val re = it.data?.getBooleanExtra("RE", false)
                alarms.add(alarm(time!! ,name!!, week!!, re!!, format!!))

                setAlarm()
            }
        }

    fun setAlarm() {
        val listAdapter = AlarmListAdapter(applicationContext)
        listAdapter.alarms = alarms

        val listView: ListView = findViewById(R.id.listview) as ListView
        listView.adapter = listAdapter
    }

}