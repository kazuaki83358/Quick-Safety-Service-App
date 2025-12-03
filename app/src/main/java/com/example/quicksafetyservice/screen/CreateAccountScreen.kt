package com.example.quicksafetyservice.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quicksafetyservice.ui.theme.DarkNavy
import com.example.quicksafetyservice.ui.theme.ScreenBackground

@Composable
fun CreateAccountScreen(
    onLoginClick: () -> Unit = {},
    onCreateAccount: () -> Unit = {}
) {
    // State for input fields
    var fullName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Main screen container with a light background
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBackground), // Assumes ScreenBackground is a light color
        contentAlignment = Alignment.Center
    ) {
        // White card that holds all the content
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.95f),
            shape = RoundedCornerShape(24.dp), // Large rounded corners
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
                    .verticalScroll(rememberScrollState()), // Allows scrolling on small screens
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Top section with Logo and Title
                Spacer(modifier = Modifier.height(48.dp))

                // 1. Logo Box
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(DarkNavy),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "QS",
                        color = Color.White,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // 2. Title and Subtitle
                Text(
                    text = "Create Account",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = DarkNavy,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = "Sign up to get started",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(32.dp))

                // 3. Input Fields
                SignUpInputField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    label = "Full Name",
                    placeholder = "Enter your full name",
                    icon = Icons.Outlined.Person
                )
                Spacer(modifier = Modifier.height(16.dp))
                SignUpInputField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    label = "Phone Number",
                    placeholder = "Enter your phone number",
                    icon = Icons.Outlined.Phone,
                    keyboardType = KeyboardType.Phone
                )
                Spacer(modifier = Modifier.height(16.dp))
                SignUpInputField(
                    value = email,
                    onValueChange = { email = it },
                    label = "Email",
                    placeholder = "Enter your email",
                    icon = Icons.Outlined.Email,
                    keyboardType = KeyboardType.Email
                )
                Spacer(modifier = Modifier.height(16.dp))
                SignUpInputField(
                    value = password,
                    onValueChange = { password = it },
                    label = "Password",
                    placeholder = "Create a password",
                    icon = Icons.Outlined.Lock,
                    keyboardType = KeyboardType.Password,
                    isPassword = true
                )

                Spacer(modifier = Modifier.height(32.dp))

                // 4. Create Account Button (Primary)
                Button(
                    onClick = onCreateAccount,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = DarkNavy),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Create Account", fontSize = 16.sp, fontWeight = FontWeight.Medium)
                }

                Spacer(modifier = Modifier.height(12.dp))

                // 5. Login Button (Secondary/Outlined)
                OutlinedButton(
                    onClick = onLoginClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, DarkNavy),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = DarkNavy)
                ) {
                    Text("Already have an account? Login", fontSize = 16.sp, fontWeight = FontWeight.Medium)
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

/**
 * A reusable input field styled for the Sign Up screen.
 */
@Composable
private fun SignUpInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = DarkNavy,
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, color = Color.Gray, fontSize = 14.sp) },
            leadingIcon = { Icon(icon, contentDescription = null, tint = Color.Gray) },
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(0xFFE0E0E0),
                focusedBorderColor = DarkNavy,
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                cursorColor = DarkNavy
            ),
            visualTransformation = if (isPassword) PasswordVisualTransformation() else androidx.compose.ui.text.input.VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCreateAccountScreen() {
    CreateAccountScreen()
}
