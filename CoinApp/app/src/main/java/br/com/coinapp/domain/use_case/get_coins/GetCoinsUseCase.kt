package br.com.coinapp.domain.use_case.get_coins

import br.com.coinapp.common.Resource
import br.com.coinapp.data.remote.response.toCoinModel
import br.com.coinapp.domain.model.Coin
import br.com.coinapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

interface GetCoinsUseCase {
    operator fun invoke(): Flow<Resource<List<Coin>>>
}

class GetCoinsUseCaseImpl @Inject constructor(
    private val repository: CoinRepository
) : GetCoinsUseCase {

    override fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoins().map {
                it.toCoinModel()
            }
            emit(Resource.Success(coins))
            //erro de status code (se nao for 200)
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Um erro ocorreu"))
            //erro de rede
        } catch (e: IOException) {
            emit(Resource.Error("Verifique a conexção com a internet"))
        }
    }
}