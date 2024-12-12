package com.ahmed.littlelemon

interface Destinations{
    val route : String
    val title : String
    val icon : Int
}

object Onboarding : Destinations{
    override val route = "Onboarding"
    override val title = "Onboarding"
    override val icon = R.drawable.ic_onboarding
}

object Home : Destinations{
    override val route = "Home"
    override val title = "Home"
    override val icon = R.drawable.ic_home
}

object Profile : Destinations{
    override val route = "Profile"
    override val title = "Profile"
    override val icon = R.drawable.ic_profile
}