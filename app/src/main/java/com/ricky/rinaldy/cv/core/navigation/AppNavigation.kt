package com.ricky.rinaldy.cv.core.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ricky.rinaldy.cv.features.contact.ContactScreen
import com.ricky.rinaldy.cv.features.experience.ExperienceScreen
import com.ricky.rinaldy.cv.features.profile.ProfileScreen
import com.ricky.rinaldy.cv.features.splash.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarRoutes = listOf(Screen.Profile.route, Screen.Experience.route, Screen.Contact.route)
    val shouldShowBottomBar = currentDestination?.route in bottomBarRoutes

    Scaffold(
        bottomBar = {
            if (shouldShowBottomBar) {
                NavigationBar {
                    bottomNavItems.forEach { screen ->
                        NavigationBarItem(
                            icon = { Icon(screen.icon, contentDescription = null) },
                            label = { Text(screen.title) },
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "splash",
            modifier = Modifier.padding(if (shouldShowBottomBar) innerPadding else PaddingValues(0.dp))
        ) {
            composable("splash") {
                SplashScreen(
                    onViewExperienceClick = {
                        navController.navigate(Screen.Experience.route) {
                            popUpTo("splash") { inclusive = true }
                        }
                    },
                    onViewOverviewClick = {
                        navController.navigate(Screen.Profile.route) {
                            popUpTo("splash") { inclusive = true }
                        }
                    }
                )
            }
            composable(Screen.Profile.route) { ProfileScreen() }
            composable(Screen.Experience.route) { ExperienceScreen() }
            composable(Screen.Contact.route) { ContactScreen() }
        }
    }
}
