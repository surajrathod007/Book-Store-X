package com.surajrathod.bookstore

import android.os.Bundle
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    public fun showToast(msg : String,long : Boolean = false){
        if(long){
            Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
        }
    }

    fun showAlert( message : String,button_title : String = "Okay", isCancellable : Boolean = true,
                   showCancel : Boolean = true, sucessBlock : () -> Unit) {
        try {
            val builder1 = AlertDialog.Builder(this)
            builder1.setMessage(message)
            builder1.setTitle(getString(R.string.app_name))
            builder1.setCancelable(isCancellable)
            builder1.setPositiveButton(button_title) { dialog, id ->
                sucessBlock()
            }
            if (showCancel) {
                builder1.setNegativeButton(
                    "Cancel"
                ) { dialog, id ->
                    dialog.cancel()
                }
            }
            val alert11 = builder1.create()
            alert11.show()
        } catch (e: Exception) {

        }
    }
}