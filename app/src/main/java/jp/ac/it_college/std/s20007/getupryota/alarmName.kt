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

        val name = binding.textView.text.toString()

        binding.add2.setOnClickListener {
            val intent = Intent()
            intent.putExtra("message", name)
            setResult(Activity.RESULT_OK, intent)
            finish()

        }
    }
}