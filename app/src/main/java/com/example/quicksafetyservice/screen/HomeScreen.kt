package com.example.quicksafetyservice.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quicksafetyservice.R
import com.example.quicksafetyservice.ui.theme.DarkIconColor
import com.example.quicksafetyservice.ui.theme.DarkNavy
import com.example.quicksafetyservice.ui.theme.LightSurface
import com.example.quicksafetyservice.ui.theme.SoftBlueGradientEnd
import com.example.quicksafetyservice.ui.theme.SoftBlueGradientStart

// Data Classes
data class Service(val name: String, val iconRes: Int)
data class Stat(val title: String, val value: String, val iconRes: Int)
data class BottomNavItem(val title: String, val iconRes: Int, val route: String)

// Lists
val services = listOf(
    Service("Security Guard", R.drawable.guard),
    Service("Bouncer", R.drawable.bouncer),
    Service("PSO", R.drawable.pso),
    Service("Servant / Aaya", R.drawable.house),
    Service("Driver", R.drawable.driver),
    Service("Electrician", R.drawable.electrician),
)

val stats = listOf(
    Stat("Available", "24/7", R.drawable.calendar),
    Stat("Workers", "500+", R.drawable.people),
)

val bottomNavItems = listOf(
    BottomNavItem("Home", R.drawable.home, "home"),
    BottomNavItem("Apply", R.drawable.apply, "apply"),
    BottomNavItem("Status", R.drawable.calendar, "status"),
    BottomNavItem("Profile", R.drawable.profile, "profile"),
)

@Composable
fun HomeScreen() {
    val scrollState = rememberScrollState()

    Scaffold(
        bottomBar = { AppBottomBar() },
        containerColor = LightSurface
    ) { paddingValues ->
        // Everything is now inside this single Column that scrolls
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Padding for the bottom bar
                .verticalScroll(scrollState) // Makes the header scroll too
        ) {
            // 1. The Blue Header (Now inside the scrollable area)
            HeaderSection(modifier = Modifier.height(200.dp))

            // 2. The Content (Services, Button, Stats)
            ServiceAndStatsSection()
        }
    }
}

@Composable
fun HeaderSection(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(SoftBlueGradientStart, SoftBlueGradientEnd),
                    start = Offset(0f, 0f),
                    end = Offset(0f, Float.POSITIVE_INFINITY)
                ),
                shape = RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp)
            )
            .padding(24.dp)
    ) {
        Column {
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Welcome back,",
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Hello, Nishant",
                            color = Color.White,
                            style = MaterialTheme.typography.headlineLarge.copy(fontSize = 24.sp),
                        )
                        Text(
                            text = " 👋",
                            fontSize = 24.sp,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.2f))
                        .clickable { /* Navigate to profile */ },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = "Profile",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ServiceAndStatsSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = (-50).dp) // Move UP to overlap with the header's rounded corners
            .padding(horizontal = 24.dp)
            .padding(bottom = 24.dp)
    ) {
        // Main Services Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.extraLarge,
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "Our Services",
                    style = MaterialTheme.typography.titleMedium.copy(color = DarkNavy),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // 2 Columns x 3 Rows grid
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    userScrollEnabled = false, // Let the parent Column scroll
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(460.dp)
                ) {
                    items(services) { service ->
                        ServiceItem(service = service)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Quick Book Button
        Button(
            onClick = { /* Handle Quick Book */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = DarkNavy),
            shape = MaterialTheme.shapes.medium,
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.flash),
                contentDescription = "Quick Book",
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Quick Book", style = MaterialTheme.typography.labelLarge)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Stats Section
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            stats.forEach { stat ->
                StatCard(stat = stat, modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun ServiceItem(service: Service) {
    Card(
        modifier = Modifier
            .height(130.dp)
            .clickable { /* Handle service click */ },
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = LightSurface),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Surface(
                modifier = Modifier.size(56.dp),
                shape = CircleShape,
                color = Color.White,
                shadowElevation = 1.dp
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(id = service.iconRes),
                        contentDescription = service.name,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = service.name,
                style = MaterialTheme.typography.labelMedium.copy(
                    color = DarkIconColor,
                    fontSize = 13.sp
                ),
                fontWeight = FontWeight.Medium,
                maxLines = 1
            )
        }
    }
}
@Composable
fun StatCard(stat: Stat, modifier: Modifier = Modifier) {
    Card(modifier = modifier.height(160.dp), // Increased height slightly to fit everything comfortably
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            // 1. Icon with Circular Background
            Surface(
                modifier = Modifier.size(40.dp),
                shape = CircleShape,
                color = Color(0xFFF0F4F8) // Light blueish background
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(id = stat.iconRes),
                        contentDescription = stat.title,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 2. The Main Value (e.g., "24/7" or "500+")
            Text(
                text = stat.value,
                style = MaterialTheme.typography.titleLarge.copy(
                    color = DarkNavy,
                    fontSize = 22.sp
                ),
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            // 3. The Title below it (e.g., "Available" or "Workers")
            Text(
                text = stat.title,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    fontSize = 14.sp
                ),
            )
        }
    }
}

@Composable
fun AppBottomBar() {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        bottomNavItems.forEachIndexed { index, item ->
            val isSelected = index == 0
            NavigationBarItem(
                selected = isSelected,
                onClick = { /* Handle navigation */ },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconRes),
                        contentDescription = item.title,
                        tint = if (isSelected) DarkNavy else Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        item.title,
                        style = MaterialTheme.typography.labelMedium,
                        color = if (isSelected) DarkNavy else Color.Gray
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}
