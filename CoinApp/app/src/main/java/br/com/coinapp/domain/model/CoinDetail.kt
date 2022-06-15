package br.com.coinapp.domain.model

import br.com.coinapp.data.remote.response.Team

data class CoinDetail(
    val id: String? = "",
    val name: String? = "",
    val description: String? = "",
    val symbol: String? = "",
    val rank: Int? = null,
    val isActive: Boolean? = null,
    val tags: List<String>? = null,
    val team: List<Team>
)
