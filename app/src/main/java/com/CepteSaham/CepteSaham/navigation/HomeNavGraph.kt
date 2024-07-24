package com.CepteSaham.CepteSaham.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.CepteSaham.CepteSaham.model.AuthViewModel
import com.CepteSaham.CepteSaham.pages.HomeScreen

@Composable
fun HomeNavGraph(navController: NavHostController, authViewModel: AuthViewModel) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomNavbarScreen.Home.route
    ) {
        composable(route = BottomNavbarScreen.Home.route) {
            HomeScreen()
        }
        composable(route = BottomNavbarScreen.Favorites.route) {
            //FavoritesPage(navController = navController)
        }
        composable(route = BottomNavbarScreen.Messages.route) {
            //BookingsPage(navController = navController)
        }
        composable(route = BottomNavbarScreen.Profile.route) {
            //ProfilePage(navController = navController, authViewModel = authViewModel)
        }
        //detailsNavGraph(navController = navController)
    }
}