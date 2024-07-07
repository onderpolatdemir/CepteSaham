package com.CepteSaham.CepteSaham.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("SPLASH")
    object Home : Screen("HOME")
    object Login : Screen("LOGIN")
    object Business : Screen("BUSINESS")
    object Guest : Screen("GUEST")
    object SignUp : Screen("SIGNUP")
    object Main : Screen("MAIN")

}