package com.example.translator.data.repository

import com.example.translator.data.data.DataModel

interface Repository<T> {

    fun getData(word: String): List<DataModel>
}