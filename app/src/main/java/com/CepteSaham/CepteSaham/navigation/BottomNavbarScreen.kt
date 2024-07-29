package com.CepteSaham.CepteSaham.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.CepteSaham.CepteSaham.R

sealed class BottomNavbarScreen(
    val route: String,
    val title: String,
    val iconresId: Int
) {
    object Home : BottomNavbarScreen(
        route = "HOME",
        title = "Ana Sayfa",
        iconresId = R.drawable.home_icon
    )

    object Favorites : BottomNavbarScreen(
        route = "FAVORITES",
        title = "Favoriler",
        iconresId = R.drawable.favourites_icon
    )

    object Messages : BottomNavbarScreen(
        route = "MESSAGES",
        title = "Mesajlar",
        iconresId = R.drawable.message_icon
    )

    object Profile : BottomNavbarScreen(
        route = "PROFILE",
        title = "Profil",
        iconresId = R.drawable.profile_icon
    )
}
