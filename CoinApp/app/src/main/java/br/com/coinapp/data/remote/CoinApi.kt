package br.com.coinapp.data.remote

import br.com.coinapp.data.remote.response.CoinDetailResponse
import br.com.coinapp.data.remote.response.CoinResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinApi {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinResponse>

    @GET("/v1/coins/{coin_id}")
    suspend fun getCoinById(
        @Path("coin_id") id: String
    ): CoinDetailResponse
}