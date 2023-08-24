package com.example.translator.data.datasource.api

import com.example.translator.data.data.DataModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("words/search")
    fun getData(@Query("search") wordToSearch: String): Observable<List<DataModel>>
}