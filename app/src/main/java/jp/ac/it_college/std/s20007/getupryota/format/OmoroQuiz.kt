package jp.ac.it_college.std.s20007.getupryota.format

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import jp.ac.it_college.std.s20007.getupryota.MainActivity
import jp.ac.it_college.std.s20007.getupryota.databinding.ActivityOmoroQuizBinding
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class OmoroQuiz : AppCompatActivity() {
    private lateinit var binding : ActivityOmoroQuizBinding
    private var data = arrayListOf<ArrayList<String>>()
    private val studentNames = arrayListOf<String>("s20010","s20014","s20016","s20024")
    private var allData = arrayListOf<ArrayList<String>>()
    private var tempData = arrayListOf<String>()
    private var crrectNum = 0
    private var  nowCount = 1
    private var crrectCount = 0
    private var count: Int= 0


    private fun printQuiz(n: Int) {
        var soloData = data[n]
        tempData = arrayListOf<String>()
        for (i in (2..5)) {
            tempData.add(soloData[i])
        }
        tempData.shuffle()
        crrectNum = tempData.indexOf(soloData[2])

        println(tempData)

        binding.questionView2.text = data[n][0]

        binding.button.text = tempData[0]
        binding.button2.text = tempData[1]
        binding.button3.text = tempData[2]
        binding.button4.text = tempData[3]

    }

    private fun clickAnswer(n: Int) {
        if (++nowCount >= 100) {
            if (crrectNum + 1 == n) {
                dialog(true)
                crrectCount++
            } else {
                dialog(false)
            }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        } else {
            if (crrectNum + 1 == n) {
                dialog(true)
                crrectCount++
            } else {
                dialog(false)
            }
            printQuiz(nowCount)
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
        if (count >= 5) {
            val intent = Intent(application, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }else{
            printQuiz(nowCount)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

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
                        println(e)
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


        super.onCreate(savedInstanceState)
        binding = ActivityOmoroQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        printQuiz(1)

        binding.button.setOnClickListener { clickAnswer(1) }
        binding.button2.setOnClickListener { clickAnswer(2) }
        binding.button3.setOnClickListener { clickAnswer(3) }
        binding.button4.setOnClickListener { clickAnswer(4) }

    }
}