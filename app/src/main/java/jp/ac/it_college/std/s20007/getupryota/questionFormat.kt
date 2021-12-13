package jp.ac.it_college.std.s20007.getupryota

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.ac.it_college.std.s20007.getupryota.databinding.ActivityQuestionFormatBinding
import jp.ac.it_college.std.s20007.getupryota.format.EnglishWordsQuiz

class questionFormat : AppCompatActivity() {
    private lateinit var binding: ActivityQuestionFormatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionFormatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.english.setOnClickListener {
            val intent = Intent(this, EnglishWordsQuiz::class.java)
            startActivity(intent)
        }
    }
}