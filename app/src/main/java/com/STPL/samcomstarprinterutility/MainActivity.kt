package com.STPL.samcomstarprinterutility

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.stpl.starmicronicsprinterlibrary.ui.TestingPrintActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent= Intent(this, TestingPrintActivity::class.java)
        startActivity(intent)
        finish()
    }
}