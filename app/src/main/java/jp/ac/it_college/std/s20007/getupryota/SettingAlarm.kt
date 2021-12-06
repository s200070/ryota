package jp.ac.it_college.std.s20007.getupryota

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.ac.it_college.std.s20007.getupryota.databinding.ActivitySettingAlarmBinding

class SettingAlarm : AppCompatActivity() {
    private lateinit var binding: ActivitySettingAlarmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySettingAlarmBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}