package com.ifs21024.tantanganpraktikum8

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Calls(
    var profile: String,
    var image: Int,
    var time: String,
) : Parcelable