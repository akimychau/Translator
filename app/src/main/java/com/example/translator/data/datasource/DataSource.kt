package com.example.translator.data.datasource

interface DataSource<T: Any> {

    suspend fun getData(word: String): T
}