package jp.ac.it_college.std.s20007.getupryota

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import jp.ac.it_college.std.s20007.getupryota.databinding.ActivitySettingAlarmBinding

class SettingAlarm : AppCompatActivity() {
    private var time = ""
    private var name = ""
    private var sound = ""
    private var format = 0
    private var week = ""
    private var re = false
    private lateinit var binding: ActivitySettingAlarmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySettingAlarmBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.alarmNameAdd.setOnClickListener {
            val intent = Intent(this, alarmName::class.java)
            getName.launch(intent)
        }

        binding.soundButton.setOnClickListener {
            val intent = Intent(this, SoundSelect::class.java)
            startActivity(intent)

        }

        binding.formatButton.setOnClickListener {
            val intent = Intent(this, questionFormat::class.java)
            getFormat.launch(intent)
        }

        binding.weeksButton.setOnClickListener {
            val intent = Intent(this, repeatWeek::class.java)
            getWeek.launch(intent)
        }

        binding.addKeep.setOnClickListener {
            val intent = Intent()
            time = binding.editTextTime.text.toString()
            re = binding.re.isChecked
            intent.putExtra("TIME", time)
            intent.putExtra("NAME", name)
            intent.putExtra("SOUND", sound)
            intent.putExtra("FORMAT", format)
            intent.putExtra("WEEK", week)
            intent.putExtra("RE", re)
            setResult(Activity.RESULT_OK, intent)
            finish()

        }
    }

    private val getName =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val value = it.data?.getStringExtra("NAME")
                binding.alarmNameAdd.text = value
                name = value.toString()
            }
        }


    private val getFormat =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val value = it.data?.getStringExtra("NAME")
                val number = it.data?.getIntExtra("NUMBER", 0)
                binding.formatButton.text = value
                format = number!!
            }
        }

    private val getWeek =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val value = it.data?.getIntegerArrayListExtra("NAME")
                var text = ""
                val we = mapOf(0 to "日", 1 to "月", 2 to "火", 3 to "水", 4 to "木", 5 to "金", 6 to "土")
                for (v in value!!) {
                    text += we[v]
                }
                binding.weeksButton.text = text
                week = value.toString()

            }
        }
}