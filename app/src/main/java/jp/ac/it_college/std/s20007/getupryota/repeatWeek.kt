package jp.ac.it_college.std.s20007.getupryota

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.RadioButton
import jp.ac.it_college.std.s20007.getupryota.databinding.ActivityRepeatWeekBinding

class repeatWeek : AppCompatActivity() {
    private lateinit var binding: ActivityRepeatWeekBinding
    val week = arrayListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepeatWeekBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.add.setOnClickListener {
            val weeks = arrayListOf(binding.sunday, binding.monday, binding.tuesday, binding.wednesday, binding.thursday, binding.friday, binding.friday)
            for (w in 0 until weeks.size) {
                if (weeks[w].isChecked) {
                    week.add(w)
            } }


            val intent = Intent()
            intent.putExtra("NAME", week)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

    }

}