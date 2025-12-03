package com.example.quicksafetyservice.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quicksafetyservice.R
import com.example.quicksafetyservice.components.AppBottomBar
import com.example.quicksafetyservice.ui.theme.DarkNavy
import com.example.quicksafetyservice.ui.theme.LightSurface

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplyScreen(
    onNavigate: (String) -> Unit // Navigation callback
) {
    BackHandler(onBack = { onNavigate("home") })
    // Define the options for the dropdowns
    val serviceOptions = listOf(
        "Security Guard",
        "Bouncer",
        "PSO",
        "Servant / Aaya",
        "Driver",
        "Electrician"
    )

    val experienceOptions = listOf("Fresher", "1 Year", "2 Years", "3-5 Years", "5+ Years")
    val availabilityOptions = listOf("Full Time", "Part Time", "Weekends Only")

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Apply as Worker",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = DarkNavy
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        // Handle back click - likely navigate to Home
                        onNavigate("home")
                    }) {
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
        // CHANGED: Passed parameters to AppBottomBar
        bottomBar = {
            AppBottomBar(
                currentRoute = "apply",
                onNavigate = onNavigate
            )
        },
        containerColor = LightSurface
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // --- Section 1: Personal Information ---
            CardSection {
                SectionTitle("Personal Information")

                AppTextField(label = "Full Name", placeholder = "Enter your full name", iconRes = R.drawable.profile)
                AppTextField(label = "Phone Number", placeholder = "Enter your phone number", iconRes = R.drawable.phone, keyboardType = KeyboardType.Phone)
                AppTextField(label = "Email", placeholder = "Enter your email", iconRes = R.drawable.email, keyboardType = KeyboardType.Email)
                AppTextField(label = "City", placeholder = "Enter your city", iconRes = R.drawable.location)
            }

            // --- Section 2: Professional Details ---
            CardSection {
                SectionTitle("Professional Details")

                AppDropdownField(
                    label = "Service Type",
                    placeholder = "Select service type",
                    iconRes = R.drawable.apply, // Make sure this drawable exists or use R.drawable.work if you have it
                    options = serviceOptions
                )
                AppDropdownField(
                    label = "Experience (Years)",
                    placeholder = "Select experience",
                    iconRes = R.drawable.calendar,
                    options = experienceOptions
                )
                AppDropdownField(
                    label = "Availability",
                    placeholder = "Select availability",
                    iconRes = R.drawable.clock,
                    options = availabilityOptions
                )
            }

            // --- Section 3: Upload Documents ---
            CardSection {
                SectionTitle("Upload Documents")

                UploadItem(title = "Aadhaar Card")
                UploadItem(title = "PAN Card")
                UploadItem(title = "Profile Photo")
            }

            // --- Submit Button ---
            Button(
                onClick = { /* Handle Submit */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = DarkNavy),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    "Submit Application",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            // Extra space for bottom bar scrolling
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun CardSection(content: @Composable ColumnScope.() -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            content = content
        )
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium.copy(
            color = DarkNavy,
            fontWeight = FontWeight.Bold
        ),
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun AppTextField(
    label: String,
    placeholder: String,
    iconRes: Int,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    var text by remember { mutableStateOf("") }

    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium.copy(color = DarkNavy),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            placeholder = { Text(placeholder, color = Color.Gray, fontSize = 14.sp) },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(24.dp)
                )
            },
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(0xFFE0E0E0),
                focusedBorderColor = DarkNavy,
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White
            ),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDropdownField(
    label: String,
    placeholder: String,
    iconRes: Int,
    options: List<String>
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf("") }

    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium.copy(color = DarkNavy),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                readOnly = true,
                value = selectedOptionText,
                onValueChange = {},
                placeholder = { Text(placeholder, color = Color.Gray, fontSize = 14.sp) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = iconRes),
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.ArrowDropDown,
                        contentDescription = "Expand",
                        tint = Color.Gray
                    )
                },
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color(0xFFE0E0E0),
                    focusedBorderColor = DarkNavy,
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White
                )
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(Color.White)
            ) {
                DropdownMenuItem(
                    text = { Text(text = placeholder, color = Color.Gray) },
                    onClick = {
                        selectedOptionText = ""
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )

                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(text = selectionOption, color = DarkNavy) },
                        onClick = {
                            selectedOptionText = selectionOption
                            expanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )
                }
            }
        }
    }
}

@Composable
fun UploadItem(title: String) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium.copy(color = DarkNavy),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        // Custom Dashed Border Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .dashedBorder(width = 1.dp, color = Color(0xFFB0BEC5), cornerRadius = 12.dp)
                .background(Color(0xFFF8F9FA), RoundedCornerShape(12.dp))
                .clickable { /* Handle Upload */ },
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    painter = painterResource(id = R.drawable.upload), // Assumed you have an upload.png/xml
                    contentDescription = "Upload",
                    tint = DarkNavy,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Click to upload",
                    style = MaterialTheme.typography.bodyMedium.copy(color = DarkNavy)
                )
                Text(
                    text = "PDF or Image",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                )
            }
        }
    }
}

// Extension function for drawing dashed borders
fun Modifier.dashedBorder(width: Dp, color: Color, cornerRadius: Dp) = this.drawBehind {
    val stroke = Stroke(
        width = width.toPx(),
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    )
    drawRoundRect(
        color = color,
        style = stroke,
        cornerRadius = CornerRadius(cornerRadius.toPx())
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewApplyScreen() {
    // CHANGED: Passed dummy lambda for preview
    ApplyScreen(onNavigate = {})
}
