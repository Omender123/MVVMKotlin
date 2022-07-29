package com.example.mvvmtask.Resporities

import com.example.mvvmtask.Model.CoinListResModel
import com.example.mymftcustomer.network.RetrofitService

class CoinListRepository (private val apiService: RetrofitService):BaseRepository() {

    suspend fun getcoinlist(

    ): CoinListResModel? {
        return try {
            safeApiCall(
                call = { apiService.getcoinlist().await() },
                error = "Error from server"
            )
        } catch (e: Exception) {
            null
        }
    }

}
