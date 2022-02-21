package jp.ac.it_college.std.s20007.getupryota.format

import android.content.Context
import android.media.MediaPlayer
import android.os.CountDownTimer
import jp.ac.it_college.std.s20007.getupryota.R

class TimeLeftCountDown(minPast: Long, countInterval: Long) : CountDownTimer(minPast, countInterval) {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var context: Context
    override fun onTick(millisUntilFinished: Long) {
    }

    override fun onFinish() {

        mediaPlayer = MediaPlayer.create(context, R.raw.music1)
        mediaPlayer.isLooping = true
        mediaPlayer.start()
    }
}