package com.surajrathod.bookstore

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var template : FrameLayout
    lateinit var loading : ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_base)
    }

    public fun showToast(msg : String,long : Boolean = false){
        if(long){
            Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
        }
    }

    fun showProgress(){
        loading = ProgressDialog(this)
        loading.setTitle("Loading")
        loading.show()
    }

    fun hideProgress(){
        if(this::loading.isInitialized && loading.isShowing){
            loading.dismiss()
        }
    }

    fun putLayout(id : Int){
        template = findViewById<FrameLayout>(R.id.contentFrame)
        template.addView(getViewFromLayout(id),0)
    }

    fun setToolBarTitle(title : String){
        toolbar = findViewById(R.id.toolbar)
        toolbar.title = title
    }
    fun getViewFromLayout(id : Int) : View{
        return layoutInflater.inflate(id,null,false)
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