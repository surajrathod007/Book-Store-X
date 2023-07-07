package com.surajrathod.bookstore.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.surajrathod.bookstore.BaseActivity
import com.surajrathod.bookstore.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        putLayout(R.layout.activity_home)
        setToolBarTitle("Books")
        //setContentView(R.layout.activity_home)
    }
}