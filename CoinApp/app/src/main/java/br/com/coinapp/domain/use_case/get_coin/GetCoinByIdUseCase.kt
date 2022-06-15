package br.com.coinapp.domain.use_case.get_coin

import br.com.coinapp.common.Resource
import br.com.coinapp.data.remote.response.toCoinDetailModel
import br.com.coinapp.domain.model.CoinDetail
import br.com.coinapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

interface GetCoinByIdUseCase {
    operator fun invoke(params: GetCoinByIdParams): Flow<Resource<CoinDetail>>
    data class GetCoinByIdParams(val id: String)
}

class GetCoinByIdUseCaseImpl @Inject constructor(
    private val repository: CoinRepository
) : GetCoinByIdUseCase {

    override fun invoke(params: GetCoinByIdUseCase.GetCoinByIdParams): Flow<Resource<CoinDetail>> =
        flow {
            try {
                emit(Resource.Loading())
                val coin =
                    repository.getCoinById(id = params.id)
                        .toCoinDetailModel()
                println(coin)
                emit(Resource.Success(coin))
                //erro de status code (se nao for 200)
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage ?: "Um erro ocorreu"))
                //erro de rede
            } catch (e: IOException) {
                emit(Resource.Error("Verifique a conexção com a internet"))
            }
        }
}