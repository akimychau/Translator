package com.example.translator.data.datasource.api

import com.example.translator.data.data.DataModel
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import io.reactivex.rxjava3.core.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitImplementation : ApiService {

    override fun getData(wordToSearch: String): Observable<List<DataModel>> {
        return getService(BaseInterceptor.interceptor).getData(wordToSearch)
    }

    private val gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    private fun getService(interceptor: Interceptor): ApiService {
        return createRetrofit(interceptor).create(ApiService::class.java)
    }

    private fun createRetrofit(interceptor: Interceptor): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_LOCATIONS)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(createOkHttpClient(interceptor))
            .build()
    }

    private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return httpClient.build()
    }

    companion object {
        private const val BASE_URL_LOCATIONS = "https://dictionary.skyeng.ru/api/public/v1/"
    }
}