package com.example.quicksafetyservice.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quicksafetyservice.R
import com.example.quicksafetyservice.components.IndicatorDot
import com.example.quicksafetyservice.ui.theme.BackgroundColor
import com.example.quicksafetyservice.ui.theme.IconCircleColor
import com.example.quicksafetyservice.ui.theme.PrimaryDarkBlue
import com.example.quicksafetyservice.ui.theme.TextDarkBlue
import com.example.quicksafetyservice.ui.theme.TextGray
import com.example.quicksafetyservice.ui.theme.TextLight

@Composable
fun OnboardingScreen(
    onSkipClick: () -> Unit, // Action to skip directly to Home
    onNextClick: () -> Unit  // Action to go to OnboardingScreen2
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Spacer to push content down from the top
        Spacer(modifier = Modifier.height(40.dp))

        // --- 1. Skip Button ---
        // When clicked, this triggers the onSkipClick lambda passed from MainActivity
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopEnd
        ) {
            TextButton(onClick = onSkipClick) {
                Text(
                    text = "Skip",
                    color = TextGray,
                    fontSize = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // --- 2. Icon and Circle Container ---
        Box(
            modifier = Modifier
                .size(160.dp)
                .clip(CircleShape)
                .background(IconCircleColor),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.shield),
                contentDescription = "Shield Icon",
                modifier = Modifier.size(60.dp)
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        // --- 3. Title Text ---
        Text(
            text = "Hire Guards & Bouncers Easily",
            color = TextDarkBlue,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        // --- 4. Description Text ---
        Text(
            text = "Professional security personnel at your fingertips. Book trusted guards and bouncers for any occasion.",
            color = TextGray,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(64.dp))

        // --- 5. Page Indicators ---
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            IndicatorDot(isActive = true) // First dot active
            Spacer(modifier = Modifier.width(8.dp))
            IndicatorDot(isActive = false)
            Spacer(modifier = Modifier.width(8.dp))
            IndicatorDot(isActive = false)
        }

        // --- 6. Spacer to push the button to the bottom ---
        Spacer(modifier = Modifier.weight(1f))

        // --- 7. Next Button ---
        // When clicked, this triggers the onNextClick lambda passed from MainActivity
        Button(
            onClick = onNextClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryDarkBlue),
            shape = MaterialTheme.shapes.medium
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Next",
                    color = TextLight,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Next Arrow",
                    tint = TextLight,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    MaterialTheme {
        OnboardingScreen(onSkipClick = {}, onNextClick = {})
    }
}
