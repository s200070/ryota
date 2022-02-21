package jp.ac.it_college.std.s20007.getupryota

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import jp.ac.it_college.std.s20007.getupryota.databinding.ActivitySoundSelectBinding


class SoundSelect : AppCompatActivity() {
    private lateinit var binding: ActivitySoundSelectBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySoundSelectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var sound = ""
        val array = arrayListOf("music1", "music2", "music3", "music4")
        binding.soundtext.text = array[0]

        binding.soundList.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, array)

        binding.soundList.setOnItemClickListener { _, _, position, _ ->
            sound = array[position]
            binding.soundtext.text = array[position]
        }

        binding.soundAdd.setOnClickListener {
            val intent = Intent()
            intent.putExtra("SOUND", sound)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}