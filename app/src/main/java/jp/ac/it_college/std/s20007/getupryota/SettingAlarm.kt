package jp.ac.it_college.std.s20007.getupryota

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.ac.it_college.std.s20007.getupryota.databinding.ActivitySettingAlarmBinding
import java.util.Arrays.toString

class SettingAlarm : AppCompatActivity() {
    private lateinit var binding: ActivitySettingAlarmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySettingAlarmBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.alarmNameAdd.setOnClickListener { ClickButton(0) }


    }
    private fun ClickButton(n:Int) {
        val intent = Intent(this, alarmName::class.java)
        startActivity(intent)
    }
}