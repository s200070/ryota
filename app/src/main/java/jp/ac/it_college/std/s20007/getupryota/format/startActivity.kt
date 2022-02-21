package jp.ac.it_college.std.s20007.getupryota.format

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.ac.it_college.std.s20007.getupryota.R
import jp.ac.it_college.std.s20007.getupryota.databinding.ActivityStartBinding

class startActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding
    lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Alarm()


        binding.startbutton.setOnClickListener {
            start()
        }


    }

    override fun onResume() {
        super.onResume()
        mediaPlayer.start()
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.pause()
    }
    private fun start() {
        val format = intent.getIntExtra("FORM", 3)
        val buttons = mapOf(0 to EnglishWordsQuiz::class.java, 1 to OmoroQuiz::class.java, 2 to CalculationQuiz::class.java)
        val intent = Intent(this, buttons[format])
        startActivity(intent)
    }

    private fun Alarm() {
        mediaPlayer = MediaPlayer.create(this, R.raw.music1)
        mediaPlayer.isLooping = true
        mediaPlayer.start()
    }
}