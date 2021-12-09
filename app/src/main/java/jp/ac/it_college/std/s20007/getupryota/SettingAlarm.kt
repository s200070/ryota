package jp.ac.it_college.std.s20007.getupryota

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            launcher.launch(intent)
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

    private val launcher = registerForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        if (result.resultCode != RESULT_OK) {

        }
        val msg = result.data?.getStringExtra("NAME")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode != 9) {return}


        if (resultCode == Activity.RESULT_OK && data != null) {
            val message = data.getStringExtra("message")
            binding.alarmName.text = message
        }else if (resultCode == Activity.RESULT_CANCELED) {
            binding.alarmName.text = "端末の戻るボタン"
        }

    }

    fun zzz() {
        val intent = Intent(this, alarmName::class.java)
        startActivityForResult(intent, 9)
    }

}