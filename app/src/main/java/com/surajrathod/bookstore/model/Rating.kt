package com.surajrathod.bookstore.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class Rating(
    val count: Int,
    val rate: Double
): Parcelable