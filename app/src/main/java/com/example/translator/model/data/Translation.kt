package com.example.translator.model.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class Translation(
    @Expose val text: String
) : Parcelable