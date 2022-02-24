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
        val sound = intent.getStringExtra("SOUND")
        Alarm(sound!!)


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
        val sound = intent.getStringExtra("SOUND")
        val format = intent.getIntExtra("FORM", 3)
        val buttons = mapOf(0 to EnglishWordsQuiz::class.java, 1 to OmoroQuiz::class.java, 2 to CalculationQuiz::class.java)
        val intent = Intent(this, buttons[format])
        intent.putExtra("SOUND", sound)
        startActivity(intent)
    }

    private fun Alarm(music: String) {
        when (music) {
            "music1" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.music1)
            }
            "music2" -> {
                mediaPlayer = MediaPlayer.create(this,R.raw.music2 )
            }
            "music3" -> {
                mediaPlayer = MediaPlayer.create(this,R.raw.music3 )
            }
            "music4" -> {
                mediaPlayer = MediaPlayer.create(this,R.raw.music4 )
            }
            else -> {
                mediaPlayer = MediaPlayer.create(this,R.raw.music1 )
            }

        }
        mediaPlayer.isLooping = true
        mediaPlayer.start()
    }
}