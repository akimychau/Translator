package com.example.translator.data.datasource.api

import com.example.translator.utils.CLIENT_ERROR_STATUS_CODE
import com.example.translator.utils.DEFAULT_RESPONSE_CODE
import com.example.translator.utils.INFO_STATUS_CODE
import com.example.translator.utils.REDIRECTION_STATUS_CODE
import com.example.translator.utils.SERVER_ERROR_STATUS_CODE
import com.example.translator.utils.SUCCESS_STATUS_CODE
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class BaseInterceptor private constructor() : Interceptor {

    private var responseCode: Int = DEFAULT_RESPONSE_CODE

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        responseCode = response.code()
        return response
    }

    fun getResponseCode(): ServerResponseStatusCode {
        var statusCode = ServerResponseStatusCode.UNDEFINED_ERROR
        when (responseCode / 100) {
            INFO_STATUS_CODE -> statusCode = ServerResponseStatusCode.INFO
            SUCCESS_STATUS_CODE -> statusCode = ServerResponseStatusCode.SUCCESS
            REDIRECTION_STATUS_CODE -> statusCode = ServerResponseStatusCode.REDIRECTION
            CLIENT_ERROR_STATUS_CODE -> statusCode = ServerResponseStatusCode.CLIENT_ERROR
            SERVER_ERROR_STATUS_CODE -> statusCode = ServerResponseStatusCode.SERVER_ERROR
        }
        return statusCode
    }

    enum class ServerResponseStatusCode {
        INFO,
        SUCCESS,
        REDIRECTION,
        CLIENT_ERROR,
        SERVER_ERROR,
        UNDEFINED_ERROR
    }

    companion object {
        val interceptor: BaseInterceptor
            get() = BaseInterceptor()
    }
}