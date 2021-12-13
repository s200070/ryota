package jp.ac.it_college.std.s20007.getupryota.format

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.ac.it_college.std.s20007.getupryota.databinding.ActivityExerciseQuizBinding

class exerciseQuiz : AppCompatActivity() {
    private lateinit var binding: ActivityExerciseQuizBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}