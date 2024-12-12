package com.ahmed.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController ) {

    val context = LocalContext.current

    // Read data from SharedPreferences
    val sharedPreferences  = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    val isRegistered = sharedPreferences.getBoolean("isRegistered", false)

    // Determine the start destination based on whether the user is registered
    val startDestination = if (isRegistered) "Home" else "Onboarding"

    //Define NavHost with navController passed
    NavHost(navController = navController, startDestination = startDestination) {
        composable("Onboarding") {
            Onboarding(navController)
        }
        composable("Home") {
            Home(navController)
        }
        composable("Profile") {
            Profile(navController)
        }

    }
}

