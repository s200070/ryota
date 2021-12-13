package jp.ac.it_college.std.s20007.getupryota.format

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import jp.ac.it_college.std.s20007.getupryota.databinding.ActivityEnglishWordsQuizBinding
import jp.ac.it_college.std.s20007.getupryota.databinding.ActivityMainBinding

class EnglishWordsQuiz : AppCompatActivity() {

    val q = "こんにちは"
    val question = arrayListOf<String>("hello", "apple", "ryota", "pama")
    val rand = question.toList().shuffled()


    private lateinit var binding: ActivityEnglishWordsQuizBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEnglishWordsQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.questionView.text = q
        binding.apply {
            chooseButton1.text = rand[0]
            chooseButton2.text = rand[1]
            chooseButton3.text = rand[2]
            chooseButton4.text = rand[3]
        }

        binding.chooseButton1.setOnClickListener {
            answer(0)
        }

        binding.chooseButton2.setOnClickListener {
            answer(1)
        }

        binding.chooseButton3.setOnClickListener {
            answer(2)
        }

        binding.chooseButton4.setOnClickListener {
            answer(3)
        }

    }

    fun answer(n:Int) {
        if (question[0] == rand[n]) {
            val dialog = SimpleDialogFragment()
            dialog.show(supportFragmentManager, "simple")
        }else {
            val dialog = SimpleDialogFragment2()
            dialog.show(supportFragmentManager, "simple")
        }
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

