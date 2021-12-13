package jp.ac.it_college.std.s20007.getupryota

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.ac.it_college.std.s20007.getupryota.databinding.ActivityQuestionFormatBinding

class questionFormat : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionFormatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityQuestionFormatBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
    }
}