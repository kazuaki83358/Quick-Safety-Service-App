package com.example.quicksafetyservice.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.quicksafetyservice.R
import com.example.quicksafetyservice.ui.theme.DarkNavy

data class BottomNavItem(val title: String, val iconRes: Int, val route: String)

val bottomNavItems = listOf(
    BottomNavItem("Home", R.drawable.home, "home"),
    BottomNavItem("Apply", R.drawable.apply, "apply"),
    BottomNavItem("Status", R.drawable.calendar, "status"),
    BottomNavItem("Profile", R.drawable.profile, "profile"),
)

@Composable
fun AppBottomBar(
    currentRoute: String,         // 1. Pass in which screen is active
    onNavigate: (String) -> Unit  // 2. Callback when an item is clicked
) {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        bottomNavItems.forEachIndexed { _, item ->
            // Check if this item is the selected one
            val isSelected = item.route == currentRoute

            val iconSize = when (item.title) {
                "Apply" -> 28.dp
                "Status" -> 20.dp
                else -> 24.dp
            }

            NavigationBarItem(
                selected = isSelected,
                onClick = { onNavigate(item.route) }, // Trigger navigation
                icon = {
                    Box(
                        modifier = Modifier.size(iconSize),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = item.iconRes),
                            contentDescription = item.title,
                            tint = if (isSelected) DarkNavy else Color.Gray,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                },
                label = {
                    Text(
                        item.title,
                        style = MaterialTheme.typography.labelMedium,
                        color = if (isSelected) DarkNavy else Color.Gray
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color(0xFFEEE5F4)
                )
            )
        }
    }
}
