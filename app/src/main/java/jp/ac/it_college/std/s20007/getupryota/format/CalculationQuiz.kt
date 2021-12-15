package jp.ac.it_college.std.s20007.getupryota.format

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import jp.ac.it_college.std.s20007.getupryota.databinding.ActivityCalculationQuizBinding
import kotlin.random.Random


class CalculationQuiz : AppCompatActivity() {

    val calculat = arrayListOf<String>("+", "-", "x", "÷")
    val rand = calculat.toList().shuffled()

    private lateinit var binding: ActivityCalculationQuizBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCalculationQuizBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.CalculationView.text = rand[0]

        ryota()

        binding.commitButton.setOnClickListener {
            val left = binding.sahenView.text.toString().toInt()
            val right = binding.uhenView.text.toString().toInt()

            var answer = 0

            if(rand[0] == calculat[0]) {answer = left + right}
            if(rand[0] == calculat[1]) {answer = left - right}
            if(rand[0] == calculat[2]) {answer = left * right}
            if(rand[0] == calculat[3]) {answer = left / right}

            if (answer == binding.answerEditText.text.toString().toInt()){
                Toast.makeText(applicationContext, "正解", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext, "不正解", Toast.LENGTH_LONG).show()

            }


        }

    }

    fun randm(n:Int) : Int{
        val randomInt = Random.nextInt(n)
        return randomInt
    }

    fun ryota() {
        if(rand[0] == calculat[0]) {
            binding.sahenView.text = randm(99).toString()
            binding.uhenView.text = randm(99).toString()
        }else if(rand[0] == calculat[1]){
            val big = randm(99).toString()
            val min = randm(99).toString()
            if (big > min) {
                binding.sahenView.text = big
                binding.uhenView.text = min
            } else {
                binding.sahenView.text = min
                binding.uhenView.text = big
            }
        }else if(rand[0] == calculat[2]) {
            binding.sahenView.text = randm(99).toString()
            binding.uhenView.text = randm(9).toString()
        }else if(rand[0] == calculat[3]) {
            val kari = randm(9)
            val kari2 = randm(9)
            val left = (kari * kari2)
            binding.sahenView.text = left.toString()
            binding.uhenView.text = kari.toString()

        }

    }



}