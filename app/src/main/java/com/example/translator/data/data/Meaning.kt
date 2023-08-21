package com.example.translator.data.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class Meaning(
    @Expose val id: Int,
    @Expose val imageUrl: String,
    @Expose val soundUrl: String,
    @Expose val translation: Translation
) : Parcelable