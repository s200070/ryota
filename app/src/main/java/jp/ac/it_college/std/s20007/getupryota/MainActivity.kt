package jp.ac.it_college.std.s20007.getupryota

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ListView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.collection.arrayMapOf
import androidx.core.content.ContextCompat
import androidx.core.database.getBlobOrNull
import jp.ac.it_college.std.s20007.getupryota.Database.DatabaseHelper
import jp.ac.it_college.std.s20007.getupryota.databinding.ActivityMainBinding
import jp.ac.it_college.std.s20007.getupryota.list.AlarmListAdapter
import jp.ac.it_college.std.s20007.getupryota.list.MyReceiver
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var _helper: DatabaseHelper
    private val idList = arrayListOf<Long>()
    private var count = 0


    val alarms =  arrayListOf<alarm>()



    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        _helper = DatabaseHelper(this)

        try {
            databaseAlarm()
            setAlarm()
        } catch (e: Exception) {
            println(e)
        }


        binding.addButton.setOnClickListener {
            val intent = Intent(this, SettingAlarm::class.java)
            startActivity(intent)
        }

        binding.listview.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, SettingAlarm::class.java)
            startActivity(intent)
        }
    }




    fun databaseAlarm() {
        val db = _helper.writableDatabase
        val sql = """
            SELECT * FROM timer
            WHERE _id = ?
        """.trimIndent()

        val ids = """
            SELECT _id FROM timer
        """.trimIndent()


        val arrayId = db.rawQuery(ids, null)

        println(arrayId)
        if (arrayId != null) {
            if ( arrayId.count > 0 ) {
                arrayId.moveToFirst()
                while (!arrayId.isAfterLast) {
                    idList.add(arrayId.getLong(0))
                    arrayId.moveToNext()
                }
            }
        }
        arrayId.close()

        for (i in idList) {
            val cursor = db.rawQuery(sql, arrayOf(i.toString()))
            println(arrayId)

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




                alarms.add(alarm(id, time, name, sound, text, repeat.toBoolean(), format.toInt()))
            }
            cursor.close()
        }

    }


    fun setAlarm() {
        val listAdapter = AlarmListAdapter(this, alarms)
        binding.listview.adapter = listAdapter
    }




}