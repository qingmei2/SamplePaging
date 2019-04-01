package com.qingmei2.samplepaging.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.qingmei2.samplepaging.R
import com.qingmei2.samplepaging.ui.basic.BasicUsageActivity
import com.qingmei2.samplepaging.ui.header_multitype.HeaderMultiTypeActivity
import com.qingmei2.samplepaging.ui.header_proxy.HeaderProxyActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBtnBasicUsage.setOnClickListener {
            startActivity(Intent(this, BasicUsageActivity::class.java))
        }
        mBtnHeaderMultiType.setOnClickListener {
            startActivity(Intent(this, HeaderMultiTypeActivity::class.java))
        }
        mBtnHeaderProxy.setOnClickListener {
            startActivity(Intent(this, HeaderProxyActivity::class.java))
        }
    }
}
