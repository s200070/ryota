package jp.ac.it_college.std.s20007.getupryota

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.ac.it_college.std.s20007.getupryota.databinding.ActivityAlarmNameBinding

class alarmName : AppCompatActivity() {
    private lateinit var binding: ActivityAlarmNameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAlarmNameBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        binding.alarmEdit.setText(name)

        binding.add2.setOnClickListener {
            val intent = Intent()
            val name = binding.alarmEdit.text.toString()
            intent.putExtra("NAME", name)
            setResult(Activity.RESULT_OK, intent)
            finish()

        }
    }
}