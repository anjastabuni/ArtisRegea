package com.example.reblymegibtabuni

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Regea(
    val imgRegea: Int,
    val nameRegea: String,
    val descRegea: String
) : Parcelable
