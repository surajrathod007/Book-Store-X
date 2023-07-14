package com.surajrathod.bookstore.utils

import java.lang.Exception

sealed class Result<T>(val data : T?=null,val msg : String?=null){
    class Success<T>(data : T,msg : String?=null) : Result<T>(data,msg)
    class Loading<T>() : Result<T>()
    class Failure<T>(msg: String?=null,val exception: Exception?=null) : Result<T>(msg = msg)
}
