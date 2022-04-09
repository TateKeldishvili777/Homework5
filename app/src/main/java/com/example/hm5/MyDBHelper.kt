package com.example.hm5

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHelper(context: Context) : SQLiteOpenHelper(context,"USERDB",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE " +
                "RUN(ID INTEGER PRIMARY KEY AUTOINCREMENT,DISTANCE INTEGER)")
        db?.execSQL("CREATE TABLE " +
                "SWIM(ID INTEGER PRIMARY KEY AUTOINCREMENT,DISTANCESWIM INTEGER)")
        db?.execSQL("CREATE TABLE " +
                "CAL(ID INTEGER PRIMARY KEY AUTOINCREMENT,AMOUNT INTEGER)")
    }
    fun getRun(): Cursor? {

        val db = this.readableDatabase

        return db.rawQuery("SELECT * FROM RUN", null)

    }
    fun getSwim(): Cursor? {

        val db = this.readableDatabase

        return db.rawQuery("SELECT * FROM SWIM", null)

    }
    fun getCal(): Cursor? {

        val db = this.readableDatabase

        return db.rawQuery("SELECT * FROM CAL", null)

    }

    fun clearData() {
        val db = this.readableDatabase

        db.execSQL("delete from RUN");
        db.execSQL("delete from SWIM");
        db.execSQL("delete from CAL");
    }

    companion object{

        val DISTANCE_COl = "DISTANCE"
        val DISTANCESWIM_COl = "DISTANCESWIM"
        val AMOUNT = "AMOUNT"

    }
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }
}