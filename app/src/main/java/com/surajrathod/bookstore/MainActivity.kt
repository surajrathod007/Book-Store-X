package com.surajrathod.bookstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.surajrathod.bookstore.ui.activities.HomeActivity
import com.surajrathod.bookstore.utils.DummyPreferenceHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : BaseActivity() {

    @Inject
    lateinit var myPrefs : DummyPreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        putLayout(R.layout.activity_main)
        setToolBarTitle("Home")
        if(myPrefs.getUserLoginStatus()){
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }else{
            showToast("Please Login",true)
        }

    }



}