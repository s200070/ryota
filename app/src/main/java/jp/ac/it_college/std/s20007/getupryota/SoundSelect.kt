package jp.ac.it_college.std.s20007.getupryota

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

        val array = arrayListOf("Music1", "Music2", "Music3", "Music4")

        binding.soundList.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, array)
    }
}