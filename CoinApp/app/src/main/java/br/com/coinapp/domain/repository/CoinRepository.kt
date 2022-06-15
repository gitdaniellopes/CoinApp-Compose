package br.com.coinapp.domain.repository

import br.com.coinapp.data.remote.response.CoinDetailResponse
import br.com.coinapp.data.remote.response.CoinResponse
import br.com.coinapp.domain.use_case.get_coin.GetCoinByIdUseCase

interface CoinRepository {

    suspend fun getCoins(): List<CoinResponse>

    suspend fun getCoinById(id: String): CoinDetailResponse
}