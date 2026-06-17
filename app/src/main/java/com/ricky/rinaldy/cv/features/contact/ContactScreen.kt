package com.ricky.rinaldy.cv.features.contact

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val ColorNavy = Color(0xFF10172A)
private val ColorTeal = Color(0xFF0D9488)
private val ColorBg = Color(0xFFF8FAFC)
private val ColorCardBg = Color(0xFFF1F5F9)

@Composable
fun ContactScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorBg)
            .verticalScroll(rememberScrollState())
    ) {
        ContactHeader()
        
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                text = "GET IN TOUCH",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = ColorTeal,
                letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Let's start a\nconversation.",
                fontSize = 42.sp,
                fontWeight = FontWeight.Bold,
                color = ColorNavy,
                lineHeight = 48.sp
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Currently open to new opportunities and interesting collaborations. Whether you have a question or just want to say hi, I'll try my best to get back to you!",
                fontSize = 15.sp,
                color = Color(0xFF64748B),
                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.height(48.dp))

            ContactCard(icon = Icons.Default.Email, title = "Email Me", desc = "Direct line for professional inquiries")
            Spacer(modifier = Modifier.height(16.dp))
            ContactCard(icon = Icons.Default.Share, title = "LinkedIn", desc = "Connect for networking")
            Spacer(modifier = Modifier.height(16.dp))
            ContactCard(icon = Icons.Default.BusinessCenter, title = "Portfolio", desc = "Explore my project showcase")

            Spacer(modifier = Modifier.height(48.dp))

            ContactForm()

            Spacer(modifier = Modifier.height(48.dp))

            ResumeDownloadCard()

            Spacer(modifier = Modifier.height(32.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Default.Info, 
                    contentDescription = null, 
                    tint = ColorTeal.copy(alpha = 0.7f), 
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Expected response time is 24-48 hours. For urgent inquiries, please reach out via LinkedIn InMail.",
                    fontSize = 11.sp,
                    color = Color.Gray,
                    lineHeight = 16.sp
                )
            }
            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}

@Composable
fun ContactHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = "Ricky Rinaldy", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = ColorNavy)
        }
        Icon(Icons.Default.MoreVert, contentDescription = null, tint = ColorNavy)
    }
}

@Composable
fun ContactCard(icon: ImageVector, title: String, desc: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
    ) {
        Row(
            modifier = Modifier.padding(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color(0xFFE0F2FE), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = ColorTeal, modifier = Modifier.size(24.dp))
            }
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ColorNavy)
                Text(text = desc, fontSize = 13.sp, color = Color.Gray)
            }
        }
    }
}

@Composable
fun ContactForm() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(ColorCardBg, RoundedCornerShape(20.dp))
            .padding(24.dp)
    ) {
        FormField(label = "FULL NAME", placeholder = "John Doe")
        Spacer(modifier = Modifier.height(20.dp))
        FormField(label = "EMAIL ADDRESS", placeholder = "your.email@example.com")
        Spacer(modifier = Modifier.height(20.dp))
        FormField(label = "MESSAGE", placeholder = "How can I help you?", isLarge = true)
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = ColorNavy),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Send Message", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(8.dp))
            Icon(Icons.AutoMirrored.Filled.Send, contentDescription = null, modifier = Modifier.size(18.dp))
        }
    }
}

@Composable
fun FormField(label: String, placeholder: String, isLarge: Boolean = false) {
    var text by remember { mutableStateOf("") }
    Column {
        Text(text = label, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.Gray, letterSpacing = 0.5.sp)
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(placeholder, fontSize = 14.sp, color = Color.Gray) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(10.dp),
            minLines = if (isLarge) 5 else 1
        )
    }
}

@Composable
fun ResumeDownloadCard() {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE2E8F0).copy(alpha = 0.8f)),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(text = "Need the details?", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = ColorNavy)
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Download my comprehensive resume for a deep dive into my professional experience, skills, and education.",
                fontSize = 13.sp,
                color = Color(0xFF475569),
                lineHeight = 20.sp
            )
            Spacer(modifier = Modifier.height(24.dp))
            TextButton(onClick = { }, contentPadding = PaddingValues(0.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Download Resume (PDF)", color = ColorTeal, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(Icons.Default.Download, contentDescription = null, tint = ColorTeal, modifier = Modifier.size(20.dp))
                }
            }
        }
    }
}
