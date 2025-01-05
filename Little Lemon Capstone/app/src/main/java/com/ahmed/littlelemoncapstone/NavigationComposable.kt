package com.ahmed.littlelemoncapstone

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ahmed.littlelemoncapstone.composables.HomeScreen
import com.ahmed.littlelemoncapstone.composables.OnboardingScreen
import com.ahmed.littlelemoncapstone.composables.ProfileScreen

@Composable
fun Navigation(navController: NavHostController, modifier: Modifier = Modifier){
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("userPres", Context.MODE_PRIVATE)
    val isRegistered = sharedPreferences.getBoolean("isRegistered", false)
    val startDestination = if (isRegistered) Home.route else Onboarding.route

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Onboarding.route){
            OnboardingScreen(navController)
        }
        composable(Home.route){
            HomeScreen(navController)
        }
        composable(Profile.route){
            ProfileScreen(navController)
        }
    }
}

@Composable
fun MyNavigation(navController: NavHostController, modifier: Modifier = Modifier){
    Navigation( navController,  modifier)
}