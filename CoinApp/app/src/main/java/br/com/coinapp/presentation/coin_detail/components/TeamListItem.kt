package br.com.coinapp.presentation.coin_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.coinapp.data.remote.response.Team
import br.com.coinapp.presentation.ui.theme.CoinAppTheme

@Composable
fun TeamListItem(
    teamMember: Team,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = teamMember.name,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.surface
        )
        Spacer(modifier = modifier.height(4.dp))
        Text(
            text = teamMember.position,
            style = MaterialTheme.typography.body2,
            fontStyle = FontStyle.Italic,
            color = MaterialTheme.colors.surface
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0x000000)
@Composable
fun TeamListItemPreview() {
    CoinAppTheme() {
        TeamListItem(
            modifier = Modifier,
            teamMember = Team(
                id = "dsdsd",
                name = "Daniel",
                position = "123"
            )
        )
    }
}