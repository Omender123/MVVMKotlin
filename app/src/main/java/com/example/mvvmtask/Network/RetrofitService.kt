package com.example.mymftcustomer.network

import com.example.mvvmtask.Model.CoinListResModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET


interface RetrofitService {

    @GET("coinlist")
    fun getcoinlist(): Deferred<Response<CoinListResModel>>


}