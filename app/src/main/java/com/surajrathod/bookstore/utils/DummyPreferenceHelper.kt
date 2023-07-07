package com.surajrathod.bookstore.utils

import android.content.SharedPreferences
import javax.inject.Inject

class DummyPreferenceHelper @Inject constructor(private val myPref: SharedPreferences) {

    fun getUserLoginStatus(): Boolean {
        return myPref.getBoolean("email", false)
    }

    fun setUserLoginStatus(status: Boolean) {
        myPref.edit().putBoolean("email", status).apply()
    }

}