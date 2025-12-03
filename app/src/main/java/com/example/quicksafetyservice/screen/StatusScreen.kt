package com.example.quicksafetyservice.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quicksafetyservice.R
import com.example.quicksafetyservice.components.AppBottomBar
import com.example.quicksafetyservice.ui.theme.DarkNavy
import com.example.quicksafetyservice.ui.theme.LightSurface

// ... (Data Models: Booking, Application stay the same) ...
data class Booking(
    val serviceName: String,
    val id: String,
    val date: String,
    val time: String,
    val status: String,
    val workerName: String? = null
)

data class Application(
    val jobRole: String,
    val id: String,
    val appliedDate: String,
    val experience: String,
    val status: String,
    val statusMessage: String? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatusScreen(
    onNavigate: (String) -> Unit // This callback handles the navigation
) {
    BackHandler(onBack = { onNavigate("home") })
    var selectedTab by remember { mutableIntStateOf(0) }

    // ... (Mock data lists stay the same) ...
    val bookings = listOf(
        Booking("Security Guard", "Booking #1", "2025-12-05", "09:00 AM", "Requested"),
        Booking("Bouncer", "Booking #2", "2025-12-10", "06:00 PM", "Approved", "Rajesh Kumar"),
        Booking("Driver", "Booking #3", "2025-11-25", "08:00 AM", "Completed", "Amit Singh")
    )

    val applications = listOf(
        Application("Security Guard", "Application #1", "2025-11-20", "3-5 Years", "Requested"),
        Application("Driver", "Application #2", "2025-11-15", "5+ Years", "Approved", "We'll contact you with job opportunities soon."),
        Application("Electrician", "Application #3", "2025-11-10", "1-3 Years", "Rejected")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "My Status",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = DarkNavy
                        )
                    )
                },
                navigationIcon = {
                    // --- HERE IS THE FIX ---
                    // When back arrow is clicked, call onNavigate with "home"
                    IconButton(onClick = { onNavigate("home") }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = DarkNavy
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = {
            // Ensure the bottom bar also knows we are on the 'status' route
            AppBottomBar(currentRoute = "status", onNavigate = onNavigate)
        },
        containerColor = LightSurface
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            StatusTabSwitcher(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                if (selectedTab == 0) {
                    items(bookings) { booking ->
                        BookingCard(booking)
                    }
                } else {
                    items(applications) { application ->
                        ApplicationCard(application)
                    }
                }
            }
        }
    }
}

// ... (Rest of your components: StatusTabSwitcher, TabButton, BookingCard, etc. remain unchanged) ...
@Composable
fun StatusTabSwitcher(selectedTab: Int, onTabSelected: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color(0xFFF5F5F5), RoundedCornerShape(12.dp))
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TabButton(
            text = "Service Bookings",
            isSelected = selectedTab == 0,
            modifier = Modifier.weight(1f),
            onClick = { onTabSelected(0) }
        )

        TabButton(
            text = "Worker Applications",
            isSelected = selectedTab == 1,
            modifier = Modifier.weight(1f),
            onClick = { onTabSelected(1) }
        )
    }
}

@Composable
fun TabButton(text: String, isSelected: Boolean, modifier: Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .clip(RoundedCornerShape(10.dp))
            .background(if (isSelected) DarkNavy else Color.Transparent)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (isSelected) Color.White else Color.Gray,
            fontSize = 14.sp,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
        )
    }
}

@Composable
fun BookingCard(booking: Booking) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = booking.serviceName, style = MaterialTheme.typography.titleMedium.copy(color = DarkNavy, fontWeight = FontWeight.Bold))
                    Text(text = booking.id, style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray))
                }
                StatusChip(status = booking.status)
            }

            Spacer(modifier = Modifier.height(12.dp))

            DetailRow(iconRes = R.drawable.calendar, text = booking.date)
            Spacer(modifier = Modifier.height(4.dp))
            DetailRow(iconRes = R.drawable.clock, text = booking.time)

            if (booking.workerName != null) {
                Spacer(modifier = Modifier.height(4.dp))
                DetailRow(iconRes = R.drawable.profile, text = "Worker: ${booking.workerName}")
            }

            if (booking.status == "Approved") {
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { /* Contact Worker */ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF54789B)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Contact Worker")
                }
            }
        }
    }
}

@Composable
fun ApplicationCard(application: Application) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = application.jobRole, style = MaterialTheme.typography.titleMedium.copy(color = DarkNavy, fontWeight = FontWeight.Bold))
                    Text(text = application.id, style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray))
                }
                StatusChip(status = application.status)
            }

            Spacer(modifier = Modifier.height(12.dp))

            DetailRow(iconRes = R.drawable.calendar, text = "Applied: ${application.appliedDate}")
            Spacer(modifier = Modifier.height(4.dp))
            // Assuming work.xml exists for experience icon, otherwise use profile
            DetailRow(iconRes = R.drawable.apply, text = "Experience: ${application.experience}")

            if (application.status == "Approved" && application.statusMessage != null) {
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFE8F5E9), RoundedCornerShape(8.dp))
                        .padding(12.dp)
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Outlined.CheckCircle,
                                contentDescription = null,
                                tint = Color(0xFF2E7D32),
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Application Approved!",
                                color = Color(0xFF2E7D32),
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = application.statusMessage,
                            color = Color(0xFF1B5E20),
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StatusChip(status: String) {
    val (backgroundColor, textColor) = when (status) {
        "Approved" -> Pair(Color(0xFFE8F5E9), Color(0xFF2E7D32))
        "Completed" -> Pair(Color(0xFFE3F2FD), Color(0xFF1565C0))
        "Rejected" -> Pair(Color(0xFFFFEBEE), Color(0xFFC62828))
        else -> Pair(Color(0xFFF5F5F5), Color(0xFF616161))
    }

    Box(
        modifier = Modifier
            .background(backgroundColor, RoundedCornerShape(16.dp))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = status,
            color = textColor,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun DetailRow(iconRes: Int, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = Color(0xFF546E7A), // Blue-Grey tint
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            color = Color(0xFF455A64),
            fontSize = 14.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStatusScreen() {
    StatusScreen(onNavigate = {})
}
