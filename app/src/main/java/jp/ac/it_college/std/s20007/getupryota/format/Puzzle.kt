package jp.ac.it_college.std.s20007.getupryota.format

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.ac.it_college.std.s20007.getupryota.databinding.ActivityPuzzleBinding

class Puzzle : AppCompatActivity() {
    private lateinit var binging : ActivityPuzzleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binging = ActivityPuzzleBinding.inflate(layoutInflater)
        setContentView(binging.root)
    }
}