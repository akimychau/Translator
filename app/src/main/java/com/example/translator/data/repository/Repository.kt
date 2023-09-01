package com.example.translator.data.repository

interface Repository<T : Any> {

    suspend fun getData(word: String): T
}