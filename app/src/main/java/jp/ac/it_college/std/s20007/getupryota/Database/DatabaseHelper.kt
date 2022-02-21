package jp.ac.it_college.std.s20007.getupryota.Database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "timer.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = """
            CREATE TABLE timer (
                _id INTEGER PRIMARY KEY,
                time TEXT,
                name TEXT,
                sound TEXT,
                sunday INTEGER,
                monday INTEGER,
                tuesday INTEGER,
                wednesday INTEGER,
                thursday INTEGER,
                friday INTEGER,
                saturday INTEGER,
                repeat TEXT,
                format LONG
            );
        """.trimIndent()
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        println("アップグレード")
    }
}