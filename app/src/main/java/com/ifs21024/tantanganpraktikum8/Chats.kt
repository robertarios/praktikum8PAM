package com.ifs21024.tantanganpraktikum8

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Chats(
    var profile: String,
    var image: Int,
    var message: String,
) : Parcelable