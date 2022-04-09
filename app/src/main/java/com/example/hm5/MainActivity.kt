package com.example.hm5

import android.annotation.SuppressLint
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var helper = MyDBHelper(applicationContext)
        var db = helper.readableDatabase
        var rs = db.rawQuery("SELECT * FROM RUN", null)

        if(rs.moveToNext())
            Toast.makeText(applicationContext,rs.getString(1),Toast.LENGTH_LONG).show()

        button1.setOnClickListener{
            var cv = ContentValues()
            cv.put("DISTANCE",editTextNumber1.text.toString())
            var totalrun = editTextNumber1.text.toString()
            db.insert("RUN",null,cv)

            editTextNumber1.setText("")
        }
        button2.setOnClickListener{
            var cv = ContentValues()
            cv.put("DISTANCESWIM",editTextNumber2.text.toString())
            db.insert("SWIM",null,cv)

            editTextNumber2.setText("")
        }
        button3.setOnClickListener{
            var cv = ContentValues()
            cv.put("AMOUNT",editTextNumber3.text.toString())
            db.insert("CAL",null,cv)

            editTextNumber3.setText("")
        }

        calculate.setOnClickListener{
            avgrunresult.setText("")
            totalrunresult.setText("")
            val db = MyDBHelper(this)
            val cursor = db.getRun()
            var sum = 0
            var num = 0

            if( cursor!!.moveToFirst()) {
                sum += (cursor.getString(cursor.getColumnIndex(MyDBHelper.DISTANCE_COl))).toInt()
                num += 1
                while (cursor.moveToNext()) {
                    sum += (cursor.getString(cursor.getColumnIndex(MyDBHelper.DISTANCE_COl))).toInt()
                    num += 1
                }
                var avg = sum / num
                avgrunresult.setText(avg.toString())
                totalrunresult.setText(sum.toString())
                cursor.close()
            }
            else{
                avgrunresult.setText("null")
                totalrunresult.setText("null")
            }
        }

        calculate1.setOnClickListener{
            avgswimresult.setText("")
            val db = MyDBHelper(this)
            val cursor = db.getSwim()
            var sum = 0
            var num = 0

            if( cursor!!.moveToFirst()) {
                sum += (cursor.getString(cursor.getColumnIndex(MyDBHelper.DISTANCESWIM_COl))).toInt()
                num += 1
                while (cursor.moveToNext()) {
                    sum += (cursor.getString(cursor.getColumnIndex(MyDBHelper.DISTANCESWIM_COl))).toInt()
                    num += 1
                }
                var avg = sum / num
                avgswimresult.setText(avg.toString())
                cursor.close()
            }
            else{
                avgswimresult.setText("null")
            }
        }

        calculate2.setOnClickListener{
            avgcalresult.setText("")
            val db = MyDBHelper(this)
            val cursor = db.getCal()
            var sum = 0
            var num = 0

            if( cursor!!.moveToFirst()) {
                sum += (cursor.getString(cursor.getColumnIndex(MyDBHelper.AMOUNT))).toInt()
                num += 1
                while (cursor.moveToNext()) {
                    sum += (cursor.getString(cursor.getColumnIndex(MyDBHelper.AMOUNT))).toInt()
                    num += 1
                }
                var avg = sum / num
                avgcalresult.setText(avg.toString())
                cursor.close()
            }
            else{
                avgcalresult.setText("null")
            }
        }

        clearData.setOnClickListener(){
            val db = MyDBHelper(this)
            db.clearData()
            avgcalresult.setText("")
            avgswimresult.setText("")
            avgrunresult.setText("")
            totalrunresult.setText("")
        }


    }
}

