package br.com.coinapp.data.remote.response

import br.com.coinapp.domain.model.Coin
import com.google.gson.annotations.SerializedName

data class CoinResponse(
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

fun CoinResponse.toCoinModel() = Coin(
    id = this.id,
    name = this.name,
    isActive = this.isActive,
    rank = this.rank,
    symbol = this.symbol
)