package com.ricky.rinaldy.cv

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.ricky.rinaldy.cv.ui.SplashScreen
import com.ricky.rinaldy.cv.ui.theme.CVAppsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CVAppsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    // Kita tidak pakai state showSplash lagi, karena ini akan jadi halaman utama
    val context = LocalContext.current

    SplashScreen(
        onViewCvClick = {
            // Nanti diganti: navController.navigate("cv")
            Toast.makeText(context, "Menuju Halaman CV...", Toast.LENGTH_SHORT).show()
        },
        onExploreWorkClick = {
            // Nanti diganti: navController.navigate("work")
            Toast.makeText(context, "Mengeksplorasi Portfolio...", Toast.LENGTH_SHORT).show()
        }
    )
}