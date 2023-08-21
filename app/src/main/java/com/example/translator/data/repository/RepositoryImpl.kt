package com.example.translator.data.repository

import com.example.translator.data.data.DataModel

class RepositoryImpl :
    Repository<List<DataModel>> {

    override fun getData(word: String): List<DataModel> {
        return example
    }

    val example = listOf(
        DataModel("qdqwdqwd"),
        DataModel("gWEGWGGWQGqdqasfasfwdqwd"),
        DataModel("YRJDJTqdqwdqwd"),
        DataModel("qdqqwdqweqqwewdqwd"),
        DataModel("qdqwvxFVAFAfASfdqwd"),
        DataModel("bhestjrygqdqwdqwd"),
        DataModel("WEGwrhqdqwdqwd"),
    )
}