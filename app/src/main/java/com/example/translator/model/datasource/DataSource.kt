package com.example.translator.model.datasource

import android.database.Observable

interface DataSource<T> {

    fun getData(word: String): Observable<T>
}