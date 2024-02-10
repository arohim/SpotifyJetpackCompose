package com.him.sama.myrealestateinvestment.feature.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.him.sama.myrealestateinvestment.common.ui.R.drawable
import com.him.sama.myrealestateinvestment.common.ui.theme.MyRealEstateInvestmentTheme
import com.him.sama.myrealestateinvestment.common.ui.theme.nautral_50

@Composable
fun HomeScreen() {
    Body()
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun Body() {
    Scaffold {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(clip = false),
            contentPadding = PaddingValues(top = 24.dp, bottom = 56.dp)
        ) {
            item { Header() }
            item { PileGraph() }
            item { Spacer(modifier = Modifier.height(24.dp)) }
            item { AssetsList(Modifier) }
        }
    }
}

@Composable
private fun AssetsList(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.White, shape = RoundedCornerShape(topStart = 22.dp, topEnd = 22.dp))
            .clip(RoundedCornerShape(topStart = 22.dp, topEnd = 22.dp))
            .padding(16.dp)
    ) {
        Text(
            text = "Properties",
            style = MaterialTheme.typography.bodySmall,
            color = nautral_50
        )
        Spacer(modifier = Modifier.height(16.dp))
        repeat(100) {
            AssetItem()
        }
    }
}

@Composable
fun AssetItem() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    painter = painterResource(id = drawable.ic_baseline_add_circle_24),
                    contentDescription = "Asset icon",
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Condo A Condo A Condo A Condo A Condo A Condo A")
            }
            Text(text = "2m", style = MaterialTheme.typography.bodyMedium)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Divider(
            thickness = 1.dp,
            color = Color.LightGray
        )
    }
}

@Composable
fun PileGraph() {
    Box(
        modifier = Modifier
            .padding(top = 24.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(350.dp)
                .background(Color.Gray, shape = CircleShape)
                .align(Alignment.Center)
        )
    }
}

@Composable
private fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Balance",
                style = MaterialTheme.typography.bodySmall,
                color = nautral_50
            )

            Text(
                text = "73,276,931.28",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black
            )
        }
        Icon(
            painter = painterResource(id = drawable.ic_baseline_add_circle_24),
            contentDescription = "Add",
        )
    }
}

@Preview
@Composable
fun PreviewBody() {
    MyRealEstateInvestmentTheme {
        Body()
    }
}
