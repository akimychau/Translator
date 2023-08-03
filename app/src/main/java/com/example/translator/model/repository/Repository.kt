package com.example.translator.model.repository

import android.database.Observable

interface Repository<T> {

    fun getData(word: String): Observable<T>
}