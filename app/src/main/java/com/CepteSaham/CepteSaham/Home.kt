package com.CepteSaham.CepteSaham

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.CepteSaham.CepteSaham.model.AuthViewModel
import com.CepteSaham.CepteSaham.navigation.BottomNavbarScreen
import com.CepteSaham.CepteSaham.navigation.HomeNavGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomePage(navController: NavHostController = rememberNavController(), authViewModel: AuthViewModel) {
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        HomeNavGraph(navController = navController, authViewModel = authViewModel)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {

    val screens = listOf(
        BottomNavbarScreen.Home,
        BottomNavbarScreen.Favorites,
        BottomNavbarScreen.Messages,
        BottomNavbarScreen.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }

    if (bottomBarDestination) {
        BottomNavigation(
            backgroundColor = colorResource(id = R.color.backgroundLight),
            contentColor = colorResource(id = R.color.black)
        ) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomNavbarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val icon = painterResource(id = screen.iconresId)

    BottomNavigationItem(
        label = {
            Text(
                text = screen.title,
                color =
                if (currentDestination?.hierarchy?.any { it.route == screen.route } == true)
                    colorResource(id = R.color.primaryColor)
                else
                    Color.Black
            )
        },
        icon = {
            Icon(
                painter = icon,
                contentDescription = "Navigation Icon",
                tint =
                if (currentDestination?.hierarchy?.any { it.route == screen.route } == true)
                    colorResource(id = R.color.primaryColor)
                else
                    Color.Black
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}