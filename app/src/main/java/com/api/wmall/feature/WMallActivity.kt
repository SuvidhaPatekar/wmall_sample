package com.api.wmall.feature

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.api.wmall.R
import kotlinx.android.synthetic.main.activity_wmall.toolbar

class WMallActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_wmall)
    setSupportActionBar(toolbar)
  }
}
