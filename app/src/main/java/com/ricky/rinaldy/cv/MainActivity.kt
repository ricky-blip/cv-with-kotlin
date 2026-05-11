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
    val context = LocalContext.current

    SplashScreen(
        onViewExperienceClick = {
            // Nanti logika navigasi ke halaman CV/Experience
            Toast.makeText(context, "Buka Halaman Experience", Toast.LENGTH_SHORT).show()
        },
        onViewOverviewClick = {
            // Nanti logika navigasi ke halaman Overview/Projects
            Toast.makeText(context, "Buka Halaman Overview", Toast.LENGTH_SHORT).show()
        }
    )
}