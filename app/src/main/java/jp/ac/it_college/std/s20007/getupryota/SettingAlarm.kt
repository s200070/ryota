package jp.ac.it_college.std.s20007.getupryota

import android.app.Activity
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.text.format.DateFormat
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import jp.ac.it_college.std.s20007.getupryota.Database.DatabaseHelper
import jp.ac.it_college.std.s20007.getupryota.databinding.ActivitySettingAlarmBinding
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

class SettingAlarm : AppCompatActivity() {
    lateinit var time_et: EditText
    private var id = 1
    private var time = ""
    private var name = ""
    private var sound = ""
    private var format = 0
    private var week = arrayListOf<Int>()
    private var re = false
    private var weekString = ""
    var sun = 0
    var mon = 0
    var tues = 0
    var wednes = 0
    var thurs = 0
    var fri = 0
    var satur = 0
    private val idList = arrayListOf<Long>()
    private lateinit var binding: ActivitySettingAlarmBinding
    private lateinit var _helper: DatabaseHelper

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySettingAlarmBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val id = intent.getIntExtra("id", 0)
        val bool = intent.getBooleanExtra("bool", false)

        if (bool) {
            reSetAlarm(id)
            binding.cancelButton.text = "消去"
            binding.addKeep.text = "決定"
            binding.cancelButton.setOnClickListener {
                delete(id)
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
            binding.addKeep.setOnClickListener {
                delete(id)
                time = binding.editTextTime.text.toString()
                re = binding.re.isChecked

                setAlarms()

                val intent = Intent(application, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
        } else {
            binding.addKeep.setOnClickListener {

                time = binding.editTextTime.text.toString()
                re = binding.re.isChecked

                setAlarms()

                val intent = Intent(application, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)


            }
        }

        setTime(bool)
        val form = mapOf(0 to "英単語", 1 to "おもろクイズ", 2 to "計算問題", 3 to "なし")
        var text = ""
        if (sun == 1) text += "日"
        if (mon == 1) text += "月"
        if (tues == 1) text += "火"
        if (wednes == 1) text += "水"
        if (thurs == 1) text += "木"
        if (fri == 1) text += "金"
        if (satur == 1) text += "土"
        binding.alarmNameAdd.setText(name)
        binding.soundButton.setText(sound)
        binding.formatButton.setText(form[format])
        binding.weeksButton.setText(weekString)
        binding.re.isChecked = re


        time_et = binding.editTextTime
        time_et.setOnClickListener {
            showTimePikerDialog()
        }

        binding.alarmNameAdd.setOnClickListener {
            val intent = Intent(this, alarmName::class.java)
            name = binding.alarmNameAdd.text.toString()
            intent.putExtra("name", name)
            getName.launch(intent)
        }

        binding.soundButton.setOnClickListener {
            val intent = Intent(this, SoundSelect::class.java)
            getSound.launch(intent)

        }

        binding.formatButton.setOnClickListener {
            val intent = Intent(this, questionFormat::class.java)
            getFormat.launch(intent)
        }

        binding.weeksButton.setOnClickListener {
            val intent = Intent(this, repeatWeek::class.java)
            getWeek.launch(intent)
        }
    }

    private val getName =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val value = it.data?.getStringExtra("NAME")
                binding.alarmNameAdd.text = value
                name = value.toString()
            }
        }


    private val getFormat =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val form = mapOf(0 to "英単語", 1 to "おもろクイズ", 2 to "計算問題", 3 to "なし")
                val value = it.data?.getStringExtra("NAME")
                val number = it.data?.getIntExtra("NUMBER", 3)
                format = number!!

                binding.formatButton.text = form[format]



            }
        }

    private val getSound =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val value = it.data?.getStringExtra("SOUND")
                sound = value.toString()
                binding.soundButton.text = sound
            }
        }

    private val getWeek =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val value = it.data?.getIntegerArrayListExtra("NAME")
                var text = ""
                val we = mapOf(0 to "日", 1 to "月", 2 to "火", 3 to "水", 4 to "木", 5 to "金", 6 to "土")
                for (v in value!!) {
                    text += we[v]
                    if (v == 0) sun = 1
                    if (v == 1) mon = 1
                    if (v == 2) tues = 1
                    if (v == 3) wednes = 1
                    if (v == 4) thurs = 1
                    if (v == 5) fri = 1
                    if (v == 6) satur = 1
                }

                binding.weeksButton.text = text
                week = value

            }
        }

    private fun showTimePikerDialog() {

        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            time_et.setText(DateFormat.format("HH:mm", cal.time))
            val cal = Calendar.getInstance()
        }

        TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
    }


    private fun reSetAlarm(id: Int) {
        _helper = DatabaseHelper(this)

        val db = _helper.writableDatabase
        val sql = """
            SELECT * FROM timer
            WHERE _id = ?
        """.trimIndent()

        val cursor = db.rawQuery(sql, arrayOf(id.toString()))

        while (cursor.moveToNext()) {
            val id = cursor.let {
                val index = it.getColumnIndex("_id")
                it.getLong(index)
            }
            val time = cursor.let {
                val index = it.getColumnIndex("time")
                it.getString(index)
            }
            val name = cursor.let {
                val index = it.getColumnIndex("name")
                it.getString(index)
            }
            val sound = cursor.let {
                val index = it.getColumnIndex("sound")
                it.getString(index)
            }
            val sun = cursor.let {
                val index = it.getColumnIndex("sunday")
                it.getLong(index).toInt()
            }
            val mon = cursor.let {
                val index = it.getColumnIndex("monday")
                it.getLong(index).toInt()
            }
            val tues = cursor.let {
                val index = it.getColumnIndex("tuesday")
                it.getLong(index).toInt()
            }
            val wednes = cursor.let {
                val index = it.getColumnIndex("wednesday")
                it.getLong(index).toInt()
            }
            val thurs = cursor.let {
                val index = it.getColumnIndex("thursday")
                it.getLong(index).toInt()
            }
            val fri = cursor.let {
                val index = it.getColumnIndex("friday")
                it.getLong(index).toInt()
            }
            val satur = cursor.let {
                val index = it.getColumnIndex("saturday")
                it.getLong(index).toInt()
            }
            val repeat = cursor.let {
                val index = it.getColumnIndex("repeat")
                it.getString(index)
            }
            val format = cursor.let {
                val index = it.getColumnIndex("format")
                it.getLong(index)
            }
            var text = ""
            if (sun == 1) text += "日"
            if (mon == 1) text += "月"
            if (tues == 1) text += "火"
            if (wednes == 1) text += "水"
            if (thurs == 1) text += "木"
            if (fri == 1) text += "金"
            if (satur == 1) text += "土"
            this.time = time
            this.name = name
            this.sound = sound
            this.weekString = text
            this.re = (repeat.toBoolean())
            this.format = format.toInt()
        }
    }

    private fun setAlarms() {
        _helper = DatabaseHelper(this)
        val db = _helper.writableDatabase

        val ids = """
            SELECT _id FROM timer
        """.trimIndent()

        val insert = """
            INSERT INTO timer
            (_id, time, name, sound, sunday, monday, tuesday, wednesday, thursday, friday, saturday, repeat, format)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """.trimIndent()

        val arrayId = db.rawQuery(ids, null)
        if (arrayId.count > 0) {
            arrayId.moveToFirst()
            while (!arrayId.isAfterLast) {
                idList.add(arrayId.getLong(0))
                arrayId.moveToNext()
            }
            id = idList.last().toInt() + 1
        }
        arrayId.close()

        val stmt = db.compileStatement(insert)

        val rep = re.toString()

        stmt.run {
            bindLong(1, id.toLong())
            bindString(2, time)
            bindString(3, name)
            bindString(4, sound)
            bindLong(5, sun.toLong())
            bindLong(6, mon.toLong())
            bindLong(7, tues.toLong())
            bindLong(8, wednes.toLong())
            bindLong(9, thurs.toLong())
            bindLong(10, fri.toLong())
            bindLong(11, satur.toLong())
            bindString(12, rep)
            bindLong(13, format.toLong())
        }

        stmt.executeInsert()

    }

    private fun setTime(bool: Boolean) {
        if (bool) {
            time_et = binding.editTextTime
            time_et.setText(time)
        } else {
            time_et = binding.editTextTime
            val result = DateFormat.format("HH:mm", Calendar.getInstance())
            time_et.setText(result)
        }

    }

    private fun  delete(id: Int) {
        _helper = DatabaseHelper(this)
        val db = _helper.writableDatabase
        val id = id
        val delete = """
            DELETE FROM timer WHERE _id = $id
        """.trimIndent()

        val stmt = db.compileStatement(delete)
        stmt.executeUpdateDelete()


    }

}