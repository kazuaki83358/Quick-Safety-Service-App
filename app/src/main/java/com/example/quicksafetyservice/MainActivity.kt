package com.example.quicksafetyservice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.quicksafetyservice.screen.HomeScreen
import com.example.quicksafetyservice.screen.OnboardingScreen
import com.example.quicksafetyservice.screen.OnboardingScreen2
import com.example.quicksafetyservice.screen.OnboardingScreen3 // Import Screen 3
import com.example.quicksafetyservice.screen.SplashScreen
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
            var currentScreen by remember { mutableIntStateOf(0) }

            // Splash Screen Timer Logic
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
                            currentScreen = 4 // Skip directly to Home
                        },
                        onNextClick = {
                            currentScreen = 2 // Go to Onboarding 2
                        }
                    )
                }
                2 -> {
                    OnboardingScreen2(
                        onSkipClick = {
                            currentScreen = 4 // Skip directly to Home
                        },
                        onNextClick = {
                            currentScreen = 3 // Go to Onboarding 3
                        }
                    )
                }
                3 -> {
                    OnboardingScreen3(
                        onSkipClick = {
                            currentScreen = 4 // Skip directly to Home
                        },
                        onGetStartedClick = {
                            currentScreen = 4 // Finish -> Go to Home
                        }
                    )
                }
                4 -> {
                    HomeScreen()
                }
            }
        }
    }
}
