package com.example.translator.domain

interface Interactor<T : Any> {

    suspend fun getData(word: String): T
}