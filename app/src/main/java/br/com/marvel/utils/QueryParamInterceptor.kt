package br.com.marvel.utils

import br.com.marvel.BuildConfig
import br.com.marvel.extensions.md5
import okhttp3.Interceptor
import okhttp3.Response

class QueryParamInterceptor : Interceptor {

    private val QUERY_TS = "ts"
    private val QUERY_API_KEY = "apikey"
    private val QUERY_HASH = "hash"

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val timestamp = System.currentTimeMillis().toString()
        val publicKey = BuildConfig.PUBLIC_KEY
        val privateKey = BuildConfig.PRIVATE_KEY

        val hash = "$timestamp$privateKey$publicKey".md5()

        val url = request.url()
            .newBuilder()
            .addQueryParameter(QUERY_TS, timestamp)
            .addQueryParameter(QUERY_HASH, hash)
            .addQueryParameter(QUERY_API_KEY, publicKey)
            .build()

        request = request.newBuilder().url(url).build()

        return chain.proceed(request)
    }

}