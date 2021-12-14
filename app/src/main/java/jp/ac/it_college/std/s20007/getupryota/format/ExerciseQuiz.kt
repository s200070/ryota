package jp.ac.it_college.std.s20007.getupryota.format

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.ac.it_college.std.s20007.getupryota.databinding.ActivityExerciseQuizBinding

class ExerciseQuiz : AppCompatActivity() {
    private lateinit var binding: ActivityExerciseQuizBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}