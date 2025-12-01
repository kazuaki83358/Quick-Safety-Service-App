package com.example.quicksafetyservice.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.quicksafetyservice.ui.theme.IndicatorActive
import com.example.quicksafetyservice.ui.theme.IndicatorInactive

@Composable
fun IndicatorDot(isActive: Boolean) {
    Box(
        modifier = Modifier
            .size(8.dp)
            .clip(CircleShape)
            .background(if (isActive) IndicatorActive else IndicatorInactive)
    )
}
