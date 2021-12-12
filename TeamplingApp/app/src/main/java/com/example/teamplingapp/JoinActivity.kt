package com.example.teamplingapp

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup

class JoinActivity : AppCompatActivity() {
    lateinit var dbManager: DBManager
    lateinit var sqlitedb: SQLiteDatabase

    lateinit var btnJoin: Button

    lateinit var edt_number: EditText
    lateinit var edt_id: EditText
    lateinit var edt_passwd: EditText
    lateinit var edt_passwd_check: EditText

    lateinit var rg_position: RadioGroup
    lateinit var rb_student: RadioButton
    lateinit var rb_professor: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join2)

        btnJoin = findViewById(R.id.btnJoin)
        edt_id = findViewById(R.id.edt_id)
        edt_number = findViewById(R.id.edt_number)
        edt_passwd = findViewById(R.id.edt_passwd)
        edt_passwd_check = findViewById(R.id.edt_passwd_check)
        rg_position = findViewById(R.id.position)
        rb_student = findViewById(R.id.student)
        rb_professor = findViewById(R.id.professor)


        dbManager = DBManager(this, "personnelDB", null, 1)

        btnJoin.setOnClickListener {
            var str_number: String = edt_number.text.toString()
            var str_id: String = edt_id.text.toString()
            var str_passwd: String = edt_passwd.text.toString()
            var str_passwd_check: String = edt_passwd_check.text.toString()
            var str_position: String = ""

            if (rg_position.checkedRadioButtonId == R.id.student) {
                str_position = rb_student.text.toString()
            }
            if (rg_position.checkedRadioButtonId == R.id.professor) {
                str_position = rb_professor.text.toString()
            }

            sqlitedb = dbManager.writableDatabase
            sqlitedb.execSQL("INSERT INTO personnel VALUES ('" + str_position + "', '"
                    + str_number + "', " + str_id + ", '" + str_passwd + "')")
            sqlitedb.close()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}