package br.com.coinapp.presentation.coin_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.coinapp.presentation.ui.theme.CoinAppTheme

@Composable
fun CoinTag(
    tag: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(Color.Black)
            .border(
                width = 1.dp,
                color = Color.Blue,
                shape = RoundedCornerShape(100.dp),

            )
            .padding(10.dp)
    ) {
        Text(
            text = tag,
            color = Color.White,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun CoinTagPreview() {
    CoinAppTheme() {
        CoinTag(
            modifier = Modifier,
            tag = "BTC"
        )
    }
}