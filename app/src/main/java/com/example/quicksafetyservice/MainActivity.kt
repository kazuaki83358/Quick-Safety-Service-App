package com.example.quicksafetyservice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.quicksafetyservice.screen.ApplyScreen
import com.example.quicksafetyservice.screen.HomeScreen
import com.example.quicksafetyservice.screen.LoginScreen
import com.example.quicksafetyservice.screen.OnboardingScreen
import com.example.quicksafetyservice.screen.OnboardingScreen2
import com.example.quicksafetyservice.screen.OnboardingScreen3
import com.example.quicksafetyservice.screen.SplashScreen
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // State Management for Navigation
            // 0 = Splash
            // 1 = Onboarding 1 (Shield)
            // 2 = Onboarding 2 (Calendar)
            // 3 = Onboarding 3 (Group)
            // 4 = Home
            // 5 = Login
            // 6 = Apply Screen (New)
            var currentScreen by remember { mutableIntStateOf(0) }

            // Helper function to handle Bottom Bar navigation
            // This is passed to HomeScreen and ApplyScreen
            val onBottomNavClick: (String) -> Unit = { route ->
                when (route) {
                    "home" -> currentScreen = 4
                    "apply" -> currentScreen = 6
                    // "status" -> currentScreen = 7 (Future)
                    // "profile" -> currentScreen = 8 (Future)
                }
            }

            // Splash Screen Timer Logic
            // This runs only once when the app starts
            LaunchedEffect(Unit) {
                delay(1500) // Wait 1.5 seconds
                if (currentScreen == 0) {
                    currentScreen = 1 // Move to first onboarding screen
                }
            }

            // Screen Switching Logic
            when (currentScreen) {
                0 -> {
                    SplashScreen()
                }
                1 -> {
                    OnboardingScreen(
                        onSkipClick = {
                            currentScreen = 5 // Skip -> Go to Login
                        },
                        onNextClick = {
                            currentScreen = 2 // Go to Onboarding 2
                        }
                    )
                }
                2 -> {
                    OnboardingScreen2(
                        onSkipClick = {
                            currentScreen = 5 // Skip -> Go to Login
                        },
                        onNextClick = {
                            currentScreen = 3 // Go to Onboarding 3
                        }
                    )
                }
                3 -> {
                    OnboardingScreen3(
                        onSkipClick = {
                            currentScreen = 5 // Skip -> Go to Login
                        },
                        onGetStartedClick = {
                            currentScreen = 5 // Finish Onboarding -> Go to Login
                        }
                    )
                }
                // 4. HOME SCREEN
                4 -> {
                    HomeScreen(
                        onNavigate = onBottomNavClick
                    )
                }
                // 5. LOGIN SCREEN
                5 -> {
                    LoginScreen(
                        onLoginSuccess = {
                            currentScreen = 4 // Login Success -> Go to Home
                        }
                    )
                }
                // 6. APPLY SCREEN
                6 -> {
                    ApplyScreen(
                        onNavigate = onBottomNavClick
                    )
                }
            }
        }
    }
}
