package jp.ac.it_college.std.s20007.getupryota.format

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import jp.ac.it_college.std.s20007.getupryota.MainActivity
import jp.ac.it_college.std.s20007.getupryota.R
import jp.ac.it_college.std.s20007.getupryota.databinding.ActivityCalculationQuizBinding
import kotlin.random.Random


class CalculationQuiz : AppCompatActivity() {
    private lateinit var binding: ActivityCalculationQuizBinding
    private lateinit var mediaPlayer: MediaPlayer
    val calculat = arrayListOf<String>("+", "-", "x", "÷")
    var rand = calculat.toList().shuffled()
    val startTime : Long = 10000
    private var count: Int= 0




    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCalculationQuizBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.count.text = "${startTime/1000}"
        binding.answerCountView.text = count.toString()

        val timer = object : CountDownTimer(startTime, 100) {
            override fun onTick(millisUntilFinished: Long) {
                binding.count.text = "${millisUntilFinished/1000}"
            }

            override fun onFinish() {
                Alarm()
            }

        }
        timer.start()
        ryota()

        binding.commitButton.setOnClickListener {
            timer.cancel()
            mediaPlayer.stop()
            val left = binding.sahenView.text.toString().toInt()
            val right = binding.uhenView.text.toString().toInt()

            var answer = 0

            if(rand[0] == calculat[0]) {answer = left + right}
            if(rand[0] == calculat[1]) {answer = left - right}
            if(rand[0] == calculat[2]) {answer = left * right}
            if(rand[0] == calculat[3]) {answer = left / right}

            if (binding.answerEditText.text.toString() == ""){
                Toast.makeText(applicationContext, "不正解", Toast.LENGTH_LONG).show()
            } else {
                if (answer == binding.answerEditText.text.toString().toInt()) {
                    Toast.makeText(applicationContext, "正解", Toast.LENGTH_LONG).show()
                    count++
                } else {
                    Toast.makeText(applicationContext, "不正解", Toast.LENGTH_LONG).show()
                }
            }
            binding.answerCountView.text = count.toString()
            if (count >= 5) {
                val intent = Intent(application, MainActivity::class.java)
                intent.flags = FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }else {
                ryota()
                timer.start()
            }
        }

    }



    fun randm(n:Int) : Int{
        val randomInt = Random.nextInt(n)+1
        return randomInt
    }

    fun ryota() {
        rand = calculat.toList().shuffled()
        binding.CalculationView.text = rand[0]
        if(rand[0] == calculat[0]) {
            binding.sahenView.text = randm(98).toString()
            binding.uhenView.text = randm(98).toString()

        }else if(rand[0] == calculat[1]){
            val big = randm(98).toString()
            val min = randm(98).toString()
            if (big > min) {
                binding.sahenView.text = big
                binding.uhenView.text = min
            } else {
                binding.sahenView.text = min
                binding.uhenView.text = big
            }

        }else if(rand[0] == calculat[2]) {
            binding.sahenView.text = randm(98).toString()
            binding.uhenView.text = randm(8).toString()

        }else if(rand[0] == calculat[3]) {
            val kari = randm(8)
            val kari2 = randm(8)
            val left = (kari * kari2)
            binding.sahenView.text = left.toString()
            binding.uhenView.text = kari.toString()

        }

    }

    private fun Alarm() {
        mediaPlayer = MediaPlayer.create(this, R.raw.music1)
        mediaPlayer.isLooping = true
        mediaPlayer.start()
    }



}