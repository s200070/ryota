package jp.ac.it_college.std.s20007.getupryota.format

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AlertDialog
import jp.ac.it_college.std.s20007.getupryota.MainActivity
import jp.ac.it_college.std.s20007.getupryota.R
import jp.ac.it_college.std.s20007.getupryota.databinding.ActivityOmoroQuizBinding
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class OmoroQuiz : AppCompatActivity() {
    private lateinit var binding: ActivityOmoroQuizBinding
    private lateinit var mediaPlayer: MediaPlayer
    private var data = arrayListOf<ArrayList<String>>()
    private val studentNames = arrayListOf<String>("s20010", "s20014", "s20016", "s20024")
    private var allData = arrayListOf<ArrayList<String>>()
    private var tempData = arrayListOf<String>()
    private var crrectNum = 0
    private var nowCount = 1
    private var count: Int = 0
    private val startTime: Long = 10000


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOmoroQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.textView8.text = count.toString()



        var a: InputStream? = null
        var i: BufferedReader? = null


        for (name in studentNames) {
            try {
                try {
                    a = assets.open("${name}.csv")
                    i = BufferedReader(InputStreamReader(a))

                    val voidString = i.readLine()
                    do {
                        var o = arrayListOf<String>()
                        val e = i.readLine()
                        //println(e)
                        for (i in e.split(",")) {
                            o.add(i)
                        }
                        allData.add(o)
                    } while (e != null)
                } finally {
                    a?.close()
                    i?.close()
                }
            } catch (e: Exception) {
                println(e)
            }
        }

        allData.shuffle()
        data = allData

        binding.timeOmo.text = "${startTime/1000}"

        timer.start()
        printQuiz(nowCount)

        binding.button1.setOnClickListener { clickAnswer(1) }
        binding.button2.setOnClickListener { clickAnswer(2) }
        binding.button3.setOnClickListener { clickAnswer(3) }
        binding.button4.setOnClickListener { clickAnswer(4) }

        binding.next.setOnClickListener {
            timer.start()
            nowCount++
            if (count >= 5) {
                val intent = Intent(application, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }else{
                nowCount ++
                if (allData.size <= nowCount) {nowCount = 1}
                printQuiz(nowCount)
            }
        }
    }

    val timer = object : CountDownTimer(startTime, 100) {
        override fun onTick(millisUntilFinished: Long) {
            binding.timeOmo.text = "${millisUntilFinished/1000}"
        }

        override fun onFinish() {
            Alarm()
        }
    }


    private fun printQuiz(n: Int) {
        binding.next.visibility = View.INVISIBLE
        var soloData = data[n]
        tempData = arrayListOf<String>()
        for (i in (2..5)) {
            tempData.add(soloData[i])
        }
        tempData.shuffle()
        crrectNum = tempData.indexOf(soloData[2])

        println(tempData)

        binding.questionView2.text = data[n][0]

        binding.button1.text = tempData[0]
        binding.button2.text = tempData[1]
        binding.button3.text = tempData[2]
        binding.button4.text = tempData[3]

    }

    private fun clickAnswer(n: Int) {
        mediaPlayer.stop()
        timer.cancel()
        binding.next.visibility = View.VISIBLE
        if (crrectNum + 1 == n) {
            dialog(true)
        } else {
            dialog(false)
        }
    }

    private fun dialog(answer: Boolean) {
        if (answer) {
            AlertDialog.Builder(this)
                .setTitle("正解")
                .setMessage("正解は${tempData[crrectNum]}です")
                .setPositiveButton("OK", null)
                .show()
            count += 1
        } else {
            AlertDialog.Builder(this)
                .setTitle("不正解")
                .setMessage("正解は${tempData[crrectNum]}です")
                .setPositiveButton("OK", null)
                .show()
        }

        binding.textView8.text = count.toString()

    }

    private fun Alarm() {
        mediaPlayer = MediaPlayer.create(this, R.raw.music1)
        mediaPlayer.isLooping = true
        mediaPlayer.start()
    }
}
