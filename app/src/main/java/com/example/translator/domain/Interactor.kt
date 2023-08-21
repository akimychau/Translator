package com.example.translator.domain

import android.database.Observable

interface Interactor<T> {

    fun getData(word: String, isOnline: Boolean): Observable<T>
}