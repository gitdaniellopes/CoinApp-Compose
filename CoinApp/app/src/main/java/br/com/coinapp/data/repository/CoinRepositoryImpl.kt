package br.com.coinapp.data.repository

import br.com.coinapp.data.remote.CoinApi
import br.com.coinapp.data.remote.response.CoinDetailResponse
import br.com.coinapp.data.remote.response.CoinResponse
import br.com.coinapp.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinApi
) : CoinRepository {

    override suspend fun getCoins(): List<CoinResponse> = api.getCoins()

    override suspend fun getCoinById(id: String): CoinDetailResponse =
        api.getCoinById(id = id)
}