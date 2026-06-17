package com.ricky.rinaldy.cv.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContactPage
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Profile : Screen("profile", "Profile", Icons.Default.Person)
    object Experience : Screen("experience", "Experience", Icons.Default.History)
    object Contact : Screen("contact", "Contact", Icons.Default.ContactPage)
}

val bottomNavItems = listOf(
    Screen.Profile,
    Screen.Experience,
    Screen.Contact
)
