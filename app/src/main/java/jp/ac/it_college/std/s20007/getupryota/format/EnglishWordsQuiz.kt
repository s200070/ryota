package jp.ac.it_college.std.s20007.getupryota.format

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import androidx.collection.arrayMapOf
import androidx.fragment.app.DialogFragment
import jp.ac.it_college.std.s20007.getupryota.MainActivity
import jp.ac.it_college.std.s20007.getupryota.R
import jp.ac.it_college.std.s20007.getupryota.databinding.ActivityEnglishWordsQuizBinding
import org.jsoup.Jsoup

class EnglishWordsQuiz : AppCompatActivity() {
    val url = "https://www.eitangokentei.com/chu1-eitango/"
    val questionArray = arrayListOf<List<String>>()
    val english = arrayMapOf<Int, String>()
    val japanees = arrayMapOf<Int, String>()
    var random = arrayListOf<Int>()
    var count = 0
    private lateinit var binding: ActivityEnglishWordsQuizBinding
    private lateinit var mediaPlayer: MediaPlayer
    private val startTime : Long = 10000
    private lateinit var timer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnglishWordsQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        timer = object : CountDownTimer(startTime, 100) {
            override fun onTick(millisUntilFinished: Long) {
                binding.timeEn.text = "${millisUntilFinished/1000}"
            }
            val bool = intent.getIntExtra("bool", 0)
            override fun onFinish() {
                if (bool == 0) {
                    val sound = intent.getStringExtra("SOUND")
                    Alarm(sound!!)
                }
            }

        }

        binding.timeEn.text = "${startTime/1000}"
        timer.start()
        run()

    }

    fun run() {
        val handler = Handler(Looper.getMainLooper())

        Thread {
            val document = Jsoup.connect(url).get()
            var selectData = document.select("tr")

            handler.post {
                selectData.forEach { questionArray.add(it.text().split(" ")) }
                for (n in 0 until questionArray.size) {
                    english[n] = questionArray[n][0]
                    japanees[n] = questionArray[n][1]
                }
                val number = 0 until questionArray.size
                random = number.shuffled() as ArrayList<Int>
                binding.questionView.text = japanees[random[0]]
                val num = arrayListOf(0,1,2,3).shuffled()


                binding.chooseButton1.text = english[random[num[0]]]
                binding.chooseButton2.text = english[random[num[1]]]
                binding.chooseButton3.text = english[random[num[2]]]
                binding.chooseButton4.text = english[random[num[3]]]

                binding.chooseButton1.setOnClickListener { answer(num[0]) }
                binding.chooseButton2.setOnClickListener { answer(num[1]) }
                binding.chooseButton3.setOnClickListener { answer(num[2]) }
                binding.chooseButton4.setOnClickListener { answer(num[3]) }
            }
        }.start()
    }


    fun answer(n:Int) {
        timer.cancel()
        mediaPlayer.stop()
        if (random[0] == random[n]) {
            val dialog = SimpleDialogFragment()
            dialog.show(supportFragmentManager, "simple")
            count++
            if (count <= 10) {
                timer.start()
                run()
            } else {
                val intent = Intent(application, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
        }else {
            val dialog = SimpleDialogFragment2()
            dialog.show(supportFragmentManager, "simple")
            run()
        }
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


class SimpleDialogFragment: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("正解")
            .setMessage("正解")
            .setPositiveButton("閉じる") { dialog, id ->
                println("dialog:$dialog which:$id")
            }
        return builder.create()
    }
}

class SimpleDialogFragment2: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("不正解")
            .setMessage("うんこ")
            .setPositiveButton("閉じる") { dialog, id ->
                println("dialog:$dialog which:$id")
            }
        return builder.create()
    }
}

