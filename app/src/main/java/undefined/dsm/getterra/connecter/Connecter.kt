package com.justgo.Connecter

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import undefined.dsm.getterra.connecter.API

object Connecter {
    lateinit var retrofit: Retrofit
    lateinit var api: API

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        retrofit = Retrofit
                .Builder()
                .baseUrl("http://ec2.istruly.sexy:1234/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()

        api = retrofit.create(API::class.java)
    }

    fun createApi() = retrofit.create(API::class.java)

}