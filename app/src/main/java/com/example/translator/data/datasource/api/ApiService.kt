package com.example.translator.data.datasource.api

import com.example.translator.data.data.DataModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("words/search")
    fun getDataAsync(@Query("search") wordToSearch: String): Deferred<List<DataModel>>
}