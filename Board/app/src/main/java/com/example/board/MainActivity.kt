package com.example.board

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var teamDBManager: TeamDBManager
    lateinit var sqlitedb: SQLiteDatabase
    lateinit var layout : LinearLayout

    lateinit var fab_write: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab_write = findViewById(R.id.fab_write)

        teamDBManager = TeamDBManager(this,"registDB",null,1)
        sqlitedb = teamDBManager.readableDatabase

        layout = findViewById(R.id.regist)

        var cursor : Cursor
        cursor = sqlitedb.rawQuery("SELECT * FROM regist",null)

        var num : Int =0
        while(cursor.moveToNext()){
            var str_title = cursor.getString(cursor.getColumnIndex("title")).toString()
            var str_content = cursor.getString(cursor.getColumnIndex("content")).toString()

            var layout_item: LinearLayout = LinearLayout(this)
            layout_item.orientation = LinearLayout.VERTICAL
            layout_item.id = num

            var tvTitle : TextView = TextView(this)
            tvTitle.text = str_title
            tvTitle.textSize = 30f
            tvTitle.setBackgroundColor(Color.LTGRAY)
            layout_item.addView(tvTitle)

            var tvContent : TextView = TextView(this)
            tvContent.text = str_content
            layout_item.addView(tvContent)



            //게시글 제목
            layout_item.setOnClickListener {
                val intent = Intent(this,ReadActivity::class.java)
                intent.putExtra("intent_title",str_title)
                startActivity(intent)
            }
            layout.addView(layout_item)
            num++
        }
        cursor.close()
        sqlitedb.close()
        teamDBManager.close()

        fab_write.setOnClickListener {
            val intent = Intent(this, WriteActivity::class.java)
            startActivity(intent)
        }

    }
}