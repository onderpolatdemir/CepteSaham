package com.CepteSaham.CepteSaham.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.CepteSaham.CepteSaham.AnimatedSplashScreen
import com.CepteSaham.CepteSaham.model.AuthViewModel
import com.CepteSaham.CepteSaham.pages.BusinessLoginPage
import com.CepteSaham.CepteSaham.pages.LoginPage
import com.CepteSaham.CepteSaham.pages.MainContent
import com.CepteSaham.CepteSaham.pages.PasswordReset
import com.CepteSaham.CepteSaham.pages.SignUpPage


fun NavGraphBuilder.authNavGraph(navController : NavHostController, authViewModel: AuthViewModel){

    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = Screen.Splash.route
    ){
        composable(route = Screen.Splash.route
        ){
            AnimatedSplashScreen(navController = navController)
        }
        composable(route = Screen.Main.route
        ){
            MainContent(
                onBusinessClick = {
                    //navController.navigateUp()
                    navController.navigate(Screen.Business.route)
                },
                onClick = {
                    //navController.navigateUp()
                    navController.navigate(Screen.Login.route)
                },
                onGuestClick = {
                    //navController.navigateUp()
                    navController.navigate(Graph.GUEST)
                }
            )
        }
        composable(route = Screen.Login.route){
            LoginPage(navController = navController, authViewModel = authViewModel)
        }
        composable(route = Screen.Business.route){
            BusinessLoginPage(navController = navController)
        }
//        composable(route = Screen.Business.route){
//            BusinessLoginPage()
//        }
        composable(route = Screen.SignUp.route){
            SignUpPage(navController = navController, authViewModel = authViewModel)
        }
        composable(route = Screen.PasswordReset.route){
            PasswordReset(navController = navController, authViewModel = authViewModel)
        }
    }
}