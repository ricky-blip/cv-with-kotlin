package com.ricky.rinaldy.cv.features.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon

// Warna Tema
private val ColorNavy = Color(0xFF10172A)
private val ColorTeal = Color(0xFF3B82F6) // Biru muda
private val ColorTextGrey = Color(0xFF64748B)

@Composable
fun SplashScreen(
    onViewExperienceClick: () -> Unit,
    onViewOverviewClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC)), // Background sangat muda
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            // 1. Logo RR dengan Aksen Dot
            Box(contentAlignment = Alignment.Center) {
                // Kotak Logo
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .shadow(12.dp, shape = RoundedCornerShape(16.dp))
                        .background(ColorNavy, shape = RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "RR",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                // Titik Aksen Kecil
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .align(Alignment.BottomEnd)
                        .offset(x = 6.dp, y = 6.dp)
                        .background(ColorTeal, shape = RoundedCornerShape(6.dp))
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // 2. Subtitle
            Text(
                text = "MOBILE ENGINEER",
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                color = ColorTeal,
                letterSpacing = 1.5.sp,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 3. Judul Besar
            Text(
                text = "Welcome to",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = ColorNavy
            )
            Text(
                text = "Ricky Rinaldy's",
                fontSize = 26.sp,
                fontStyle = FontStyle.Italic, // Miring sesuai gambar
                color = Color(0xFF475569),
                modifier = Modifier.padding(vertical = 2.dp)
            )
            Text(
                text = "Portfolio",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = ColorNavy
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 4. Deskripsi
            Text(
                text = "Flutter Developer with 3+ years of experience building scalable and high-performance mobile applications, especially for business operations and internal systems.",
                fontSize = 14.sp,
                color = ColorTextGrey,
                lineHeight = 22.sp,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            Spacer(modifier = Modifier.height(48.dp))

            // 5. Tombol "View Experience" (Solid)
            Button(
                onClick = onViewExperienceClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ColorNavy),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "View Experience",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Arrow",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 6. Tombol "View Overview" (Outlined)
            OutlinedButton(
                onClick = onViewOverviewClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = ColorNavy),
                border = ButtonDefaults.outlinedButtonBorder.copy(width = 1.5.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "View Overview",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = ColorNavy
                )
            }
        }
    }
}