package jp.ac.it_college.std.s20007.getupryota

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.quickaccesswallet.GetWalletCardsCallback
import android.view.accessibility.AccessibilityManager
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import jp.ac.it_college.std.s20007.getupryota.databinding.ActivitySettingAlarmBinding
import java.util.Arrays.toString
import kotlin.contracts.contract

class SettingAlarm : AppCompatActivity() {
    private lateinit var binding: ActivitySettingAlarmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySettingAlarmBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.alarmNameAdd.setOnClickListener {
            val intent = Intent(this, alarmName::class.java)
            getResult.launch(intent)

        }

        binding.soundButton.setOnClickListener {
            val intent = Intent(this, soundSelect::class.java)
            startActivity(intent)
        }

        binding.formatButton.setOnClickListener {
            val intent = Intent(this, questionFormat::class.java)
            startActivity(intent)
        }

        binding.weeksButton.setOnClickListener {
            val intent = Intent(this, repeatWeek::class.java)
            startActivity(intent)
        }

        binding.re.setOnClickListener{
        }
    }

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val value = it.data?.getStringExtra("NAME")
                binding.alarmNameAdd.text = value
            }
        }
}