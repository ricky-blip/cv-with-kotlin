package com.ricky.rinaldy.cv.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Warna berdasarkan gambar desain
private val ColorNavy = Color(0xFF10172A)
private val ColorTeal = Color(0xFF3B82F6) // Biru agak muda untuk subtitle
private val ColorTextGrey = Color(0xFF64748B)

@Composable
fun SplashScreen(
    onViewCvClick: () -> Unit,
    onExploreWorkClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC)), // Background abu sangat muda
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            // 1. Logo RR
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .shadow(12.dp, shape = RoundedCornerShape(16.dp))
                    .background(ColorNavy, shape = RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "RR",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            // 2. Subtitle (Flutter Developer & Business...)
            Text(
                text = "FLUTTER DEVELOPER & BUSINESS\nWORKFLOW SPECIALIST",
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF0EA5E9), // Warna biru cerah
                letterSpacing = 2.sp,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 3. Judul Besar (Welcome to Ricky Rinaldy's Portfolio)
            Text(
                text = "Welcome to",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = ColorNavy
            )
            Text(
                text = "Ricky Rinaldy's",
                fontSize = 28.sp,
                fontStyle = FontStyle.Italic,
                color = Color(0xFF64748B) // Warna abu-abu biru
            )
            Text(
                text = "Portfolio",
                fontSize = 28.sp,
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

            // 5. Tombol View CV
            Button(
                onClick = onViewCvClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ColorNavy),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "View CV",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "→", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 6. Link Explore Work
            TextButton(onClick = onExploreWorkClick) {
                Text(
                    text = "Explore Work",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = ColorNavy
                )
            }
        }
    }
}