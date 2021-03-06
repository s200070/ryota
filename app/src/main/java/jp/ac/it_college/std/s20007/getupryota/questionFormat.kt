package jp.ac.it_college.std.s20007.getupryota


import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.ac.it_college.std.s20007.getupryota.databinding.ActivityQuestionFormatBinding
import jp.ac.it_college.std.s20007.getupryota.format.CalculationQuiz
import jp.ac.it_college.std.s20007.getupryota.format.EnglishWordsQuiz
import jp.ac.it_college.std.s20007.getupryota.format.OmoroQuiz
import jp.ac.it_college.std.s20007.getupryota.format.ExerciseQuiz


class questionFormat : AppCompatActivity() {
    private lateinit var binding: ActivityQuestionFormatBinding
    var select = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionFormatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.english.setOnClickListener {
            col(0)
            binding.tryButton.isEnabled = true
        }
        binding.puzzle.setOnClickListener {
            col(1)
            binding.tryButton.isEnabled = true
        }
        binding.calculation.setOnClickListener {
            col(2)
            binding.tryButton.isEnabled = true
        }

        binding.none.setOnClickListener {
            col(3)
            binding.tryButton.isEnabled = false
        }

        binding.tryButton.setOnClickListener {
            intents(select)
        }


        binding.formatAdd.setOnClickListener {
            val buttons = mapOf(0 to binding.english, 1 to binding.puzzle, 2 to binding.calculation,  3 to binding.none)
            val intent = Intent()
            val name = buttons[select]!!.text.toString()
            intent.putExtra("NAME", name)
            intent.putExtra("NUMBER", select)
            setResult(Activity.RESULT_OK, intent)
            finish()

        }




    }

    private fun intents(n: Int) {
        val buttons = mapOf(0 to EnglishWordsQuiz::class.java, 1 to OmoroQuiz::class.java, 2 to CalculationQuiz::class.java)
        val intent = Intent(this, buttons[n])
        intent.putExtra("bool", 1)
        startActivity(intent)
    }

    private fun col(n : Int) {
        val buttons = mapOf(0 to binding.english, 1 to binding.puzzle, 2 to binding.calculation, 3 to binding.none)
        buttons[n]!!.setBackgroundColor(Color.	rgb(192,192,192))
        select = n

        for (a in 0 until 4) {
            if (n != a) {
                buttons[a]!!.setBackgroundColor(Color.	rgb(98,11,238))
            }
        }


    }
}