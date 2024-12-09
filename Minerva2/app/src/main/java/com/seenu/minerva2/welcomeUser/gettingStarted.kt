package com.seenu.minerva2.welcomeUser

import com.seenu.minerva2.ui.theme.MinervaTypography
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.seenu.minerva2.R
@Composable
fun WelcomeScreen(welcomeToLogin:()->Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.group), contentDescription = null,
            modifier = Modifier.size(550.dp),
            colorFilter = ColorFilter.tint(colorScheme.primary)
        )
        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 16.dp
            ),
            modifier = Modifier
                .fillMaxHeight(0.99f)
                .fillMaxWidth(0.95f)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(10.dp)
            ) {
                Text(text = "Create Habits That Last.",
                    style = MinervaTypography.displaySmall,
                    modifier = Modifier.padding(6.dp))

                Spacer(modifier = Modifier.height(10.dp))

                Text(text = "Welcome to Minerva, your partner in cultivating sustainable habits and achieving your goals. " +
                        "Track your tasks effortlessly and receive personalized insights that guide you toward consistent progress. " +
                        "Discover how small changes can lead to meaningful growth.",
                    style = MinervaTypography.bodyLarge,
                    modifier = Modifier.padding(6.dp))

                Spacer(modifier = Modifier.height(15.dp))

                Button(onClick = welcomeToLogin,
                    modifier = Modifier
                        .fillMaxWidth(0.99f)
                        .padding(6.dp)
                        .height(60.dp),) {
                    Text(text = "Get Started", style = MinervaTypography.bodyLarge)
                }
            }
        }
    }
}


@Preview
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen {}
}