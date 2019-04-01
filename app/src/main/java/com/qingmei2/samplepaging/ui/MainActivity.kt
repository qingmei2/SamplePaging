package com.qingmei2.samplepaging.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.qingmei2.samplepaging.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBtnBasicUsage.setOnClickListener {
            startActivity(Intent(this, BasicUsageActivity::class.java))
        }
        mBtnHeaderUsage.setOnClickListener {
            startActivity(Intent(this, HeaderUsageActivity::class.java))
        }
    }
}
