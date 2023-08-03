package com.example.translator.model.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataModel(
    @Expose val id: Int,
    @Expose val meanings: List<Meaning>,
    @Expose val text: String
) : Parcelable