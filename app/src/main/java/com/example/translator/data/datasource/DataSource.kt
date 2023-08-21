package com.example.translator.data.datasource

import android.database.Observable

interface DataSource<T> {

    fun getData(word: String): Observable<T>
}