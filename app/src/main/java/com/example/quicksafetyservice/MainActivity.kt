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
import com.example.quicksafetyservice.screen.ProfileScreen // Import ProfileScreen
import com.example.quicksafetyservice.screen.SplashScreen
import com.example.quicksafetyservice.screen.StatusScreen
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // State Management for Navigation
            // 0 = Splash
            // 1 = Onboarding 1
            // 2 = Onboarding 2
            // 3 = Onboarding 3
            // 4 = Home
            // 5 = Login
            // 6 = Apply Screen
            // 7 = Status Screen
            // 8 = Profile Screen (NEW)
            var currentScreen by remember { mutableIntStateOf(0) }

            // Helper function to handle Bottom Bar navigation
            val onBottomNavClick: (String) -> Unit = { route ->
                when (route) {
                    "home" -> currentScreen = 4
                    "apply" -> currentScreen = 6
                    "status" -> currentScreen = 7
                    "profile" -> currentScreen = 8 // Handle profile route
                }
            }

            // Splash Screen Timer Logic
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

                // 4. HOME SCREEN
                4 -> HomeScreen(onNavigate = onBottomNavClick)

                // 5. LOGIN SCREEN
                5 -> LoginScreen(onLoginSuccess = { currentScreen = 4 })

                // 6. APPLY SCREEN
                6 -> ApplyScreen(onNavigate = onBottomNavClick)

                // 7. STATUS SCREEN
                7 -> StatusScreen(onNavigate = onBottomNavClick)

                // 8. PROFILE SCREEN (NEW)
                8 -> {
                    ProfileScreen(
                        onNavigate = onBottomNavClick,
                        onLogout = {
                            currentScreen = 5 // Logout goes back to Login Screen
                        }
                    )
                }
            }
        }
    }
}
