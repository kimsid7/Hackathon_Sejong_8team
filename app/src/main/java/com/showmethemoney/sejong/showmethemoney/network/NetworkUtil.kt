package com.showmethemoney.sejong.showmethemoney.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by LeeWoochan on 2017-06-26.
 */
class NetworkUtil {
    companion object  {
        private val url = "http://52.78.20.5/"
        fun request(): Service {
            return Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(url)
                    .build()
                    .create(Service::class.java)
        }
    }
}