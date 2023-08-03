package com.example.translator.presenter

import android.database.Observable

interface Interactor<T> {

    fun getData(word: String, isOnline: Boolean): Observable<T>
}