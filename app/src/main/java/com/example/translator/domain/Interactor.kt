package com.example.translator.domain

import io.reactivex.rxjava3.core.Observable

interface Interactor<T: Any> {

    fun getData(word: String): Observable<T>
}