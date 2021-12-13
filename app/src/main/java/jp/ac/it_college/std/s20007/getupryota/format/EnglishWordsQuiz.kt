package jp.ac.it_college.std.s20007.getupryota.format

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.ac.it_college.std.s20007.getupryota.databinding.ActivityEnglishWordsQuizBinding
import android.widget.Toast
import jp.ac.it_college.std.s20007.getupryota.databinding.ActivityMainBinding

class EnglishWordsQuiz : AppCompatActivity() {

    private lateinit var binding: ActivityEnglishWordsQuizBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEnglishWordsQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toastMessage = "トースト"

        binding.chooseButton1.setOnClickListener {
            val toast = Toast.makeText(applicationContext, toastMessage, Toast.LENGTH_LONG)
            toast.show()
        }
    }
}