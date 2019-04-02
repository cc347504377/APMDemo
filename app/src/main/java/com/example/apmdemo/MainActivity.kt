package com.example.apmdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bt_click.setOnClickListener {
            Thread.sleep(5000)
            Toast.makeText(this@MainActivity, "haha", Toast.LENGTH_SHORT).show()
        }
    }
}
