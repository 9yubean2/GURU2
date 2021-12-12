package com.example.checkboxapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var checkApple : CheckBox
    lateinit var checkBanana : CheckBox
    lateinit var checkOrange : CheckBox

    var listener = CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
        if(isChecked)
            when(buttonView.id){
                R.id.checkApple -> Toast.makeText(applicationContext, "사과", Toast.LENGTH_SHORT).show()
                R.id.checkBanana -> Toast.makeText(applicationContext, "바나나", Toast.LENGTH_SHORT).show()
                R.id.checkOrange -> Toast.makeText(applicationContext, "오렌지", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkApple = findViewById(R.id.checkApple)
        checkBanana = findViewById(R.id.checkBanana)
        checkOrange = findViewById(R.id.checkOrange)

        checkApple.setOnCheckedChangeListener(listener)
        checkBanana.setOnCheckedChangeListener(listener)
        checkOrange.setOnCheckedChangeListener(listener)
    }

}