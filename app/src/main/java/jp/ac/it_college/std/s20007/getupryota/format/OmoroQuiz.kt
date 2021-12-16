package jp.ac.it_college.std.s20007.getupryota.format

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.ac.it_college.std.s20007.getupryota.databinding.ActivityOmoroQuizBinding

class OmoroQuiz : AppCompatActivity() {
    private lateinit var binging : ActivityOmoroQuizBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binging = ActivityOmoroQuizBinding.inflate(layoutInflater)
        setContentView(binging.root)
    }
}