package com.example.quicksafetyservice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.quicksafetyservice.screen.* // Import all screens
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // State Management for Navigation
            // ... (0-8 are existing states)
            // 9 = Create Account Screen (NEW)
            var currentScreen by remember { mutableIntStateOf(0) }

            // ... (onBottomNavClick and LaunchedEffect remain the same)
            val onBottomNavClick: (String) -> Unit = { route ->
                when (route) {
                    "home" -> currentScreen = 4
                    "apply" -> currentScreen = 6
                    "status" -> currentScreen = 7
                    "profile" -> currentScreen = 8
                }
            }

            LaunchedEffect(Unit) {
                delay(1500)
                if (currentScreen == 0) {
                    currentScreen = 1
                }
            }

            // Screen Switching Logic
            when (currentScreen) {
                0 -> SplashScreen()
                1 -> OnboardingScreen(onSkipClick = { currentScreen = 5 }, onNextClick = { currentScreen = 2 })
                2 -> OnboardingScreen2(onSkipClick = { currentScreen = 5 }, onNextClick = { currentScreen = 3 })
                3 -> OnboardingScreen3(onSkipClick = { currentScreen = 5 }, onGetStartedClick = { currentScreen = 5 })
                4 -> HomeScreen(onNavigate = onBottomNavClick)

                // 5. LOGIN SCREEN
                5 -> {
                    LoginScreen(
                        onLoginSuccess = {
                            currentScreen = 4 // Login Success -> Go to Home
                        },
                        // --- Add this block ---
                        onCreateAccountClick = {
                            currentScreen = 9 // Go to Create Account
                        }
                    )
                }

                6 -> ApplyScreen(onNavigate = onBottomNavClick)
                7 -> StatusScreen(onNavigate = onBottomNavClick)
                8 -> ProfileScreen(onNavigate = onBottomNavClick, onLogout = { currentScreen = 5 })

                // 9. CREATE ACCOUNT SCREEN (NEW)
                9 -> {
                    CreateAccountScreen(
                        onCreateAccount = {
                            // After creating account, navigate to Home
                            currentScreen = 4
                        },
                        onLoginClick = {
                            // If user already has account, go back to Login
                            currentScreen = 5
                        }
                    )
                }
            }
        }
    }
}
