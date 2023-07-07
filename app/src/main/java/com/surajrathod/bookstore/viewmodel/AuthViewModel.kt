package com.surajrathod.bookstore.viewmodel

import androidx.lifecycle.ViewModel
import java.util.Dictionary

class AuthViewModel : ViewModel() {
    val validEmails = mapOf("abc" to 123,"pqr" to 456)

    fun isLogin(email : String,pass : Int) : Boolean{
        var isLog = false
        validEmails.forEach { e, p ->
            if(email==e && pass==p){
                isLog = true
            }
        }
        return isLog
    }
}