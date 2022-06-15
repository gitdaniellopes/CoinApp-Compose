package br.com.coinapp.presentation.coin_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.coinapp.data.remote.response.Team
import br.com.coinapp.domain.model.CoinDetail
import br.com.coinapp.presentation.coin_detail.components.CoinTag
import br.com.coinapp.presentation.coin_detail.components.TeamListItem
import br.com.coinapp.presentation.ui.theme.CoinAppTheme
import com.google.accompanist.flowlayout.FlowRow
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun CoinDetailScreen(
    id: String,
    modifier: Modifier = Modifier,
    coinDetailViewModel: CoinDetailViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        coinDetailViewModel.getCoins(id)
    }

    val state = coinDetailViewModel.state.value
    Box(modifier = modifier.fillMaxSize()) {
        state.coin?.let { coinDetail ->

            LazyColumn(
                modifier = modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ) {
                item {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${coinDetail.rank}. ${coinDetail.name} (${coinDetail.symbol})",
                            style = MaterialTheme.typography.h5,
                            //peso para nao se sobrepor ao texto de ativo logo abaixo
                            modifier = Modifier.weight(8f),
                            color = MaterialTheme.colors.surface
                        )
                        Text(
                            text = if (coinDetail.isActive == true) "active" else "inactive",
                            color = if (coinDetail.isActive == true) Color.Green else Color.Red,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.End,
                            modifier = modifier
                                .align(CenterVertically)
                                .weight(2f)
                        )
                    }
                    Spacer(modifier = modifier.height(15.dp))
                    Text(
                        text = coinDetail.description.orEmpty(),
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.surface
                    )
                    Spacer(modifier = modifier.height(15.dp))
                    Text(
                        text = "Tags",
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.surface
                    )
                    Spacer(modifier = modifier.height(15.dp))
                    // Uma linha que ira envolver os elementos se eles excederem os limites.
                    //cada tag vai ficar nessa linha uma ao lado da outra
                    FlowRow(
                        mainAxisSpacing = 10.dp,
                        //espaço entre os itens
                        crossAxisSpacing = 10.dp,
                        modifier = modifier.fillMaxWidth()
                    ) {
                        coinDetail.tags?.forEach { tag ->
                            CoinTag(tag = tag)
                        }
                    }
                    Spacer(modifier = modifier.height(15.dp))
                    Text(
                        text = "Team members",
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.surface
                    )
                    Spacer(modifier = modifier.height(15.dp))
                }
                items(coinDetail.team) { teamMember ->
                    TeamListItem(
                        teamMember = teamMember,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )
                    Divider(
                        color = Color.DarkGray
                    )
                }
            }
        }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun CoinDetailScreenPreview() {
    CoinAppTheme() {
        CoinDetailScreen(
            modifier = Modifier,
            id = "BTC",
        )
    }
}

private fun coinDetail() = CoinDetail(
    id = "aave-new",
    name = "Aave",
    description = "Decentralized Lending on Ethereum Network - Lending Ethereum",
    symbol = "AAVE",
    rank = 61,
    isActive = true,
    tags = listOf(
        "Platform",
        "Smart Contracts",
        "ETH Token",
        "Marketplace",
        "Loans",
        "Finance Banking",
        "DeFi"
    ),
    team = listOf(
        Team(
            id = "ville-valkonen",
            name = "Ville Valkonen",
            position = "Head of Technical Support"
        ),
        Team(
            id = "tomoaki-sato",
            name = "Tomoaki Sato",
            position = "Advisor"
        ),
        Team(
            id = "jon-matonis",
            name = "Jon Matonis",
            position = "Advisor"
        ),
        Team(
            id = "rob-viglione",
            name = "Rob Viglione",
            position = "Advisor"
        ),
        Team(
            id = "adnan-javed",
            name = "Adnan Javed",
            position = "Legal Strategy"
        ),
    )
)