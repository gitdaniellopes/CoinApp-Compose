package br.com.coinapp.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.coinapp.common.Resource
import br.com.coinapp.domain.use_case.get_coin.GetCoinByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * SavedStateHandle
 * o id vai estar contido nesse estado de instancia, onde vamos passar ele usando navigation.
 *
 * utilizamos o init para pegar atraves do savedStateHandle esse id que foi passado
 * */

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val useCase: GetCoinByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state

//    init {
//        savedStateHandle.get<String>(PARAM_COIN_ID)?.let { coinId ->
//            getCoins(coinId)
//        }
//    }

    fun getCoins(coinId: String) {
        useCase(
            GetCoinByIdUseCase.GetCoinByIdParams(
                id = coinId
            )
        ).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CoinDetailState(coin = result.data)
                }
                is Resource.Error -> {
                    _state.value = CoinDetailState(error = result.message ?: "Um erro ocorrou.")
                }
                is Resource.Loading -> {
                    _state.value = CoinDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}