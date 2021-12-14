package jp.ac.it_college.std.s20007.getupryota.format

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.ac.it_college.std.s20007.getupryota.databinding.ActivityCalculationQuizBinding

class CalculationQuiz : AppCompatActivity() {
    private lateinit var binding: ActivityCalculationQuizBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculationQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}