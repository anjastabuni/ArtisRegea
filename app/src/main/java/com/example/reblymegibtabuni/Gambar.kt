package com.example.reblymegibtabuni

import com.bumptech.glide.annotation.Excludes

data class Gambar(
    var name:String? = null,
    var imageUrl: String? = null,
    var description:String? = null,

    @get:Excludes
    @set:Excludes
    var key:String? null
)
