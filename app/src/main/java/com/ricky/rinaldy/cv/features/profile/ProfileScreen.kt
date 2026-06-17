package com.ricky.rinaldy.cv.features.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val ColorNavy = Color(0xFF10172A)

private val ColorSoftNavy = Color(0xFF1E3A5F)
private val ColorTeal = Color(0xFF0D9488)
private val ColorBg = Color(0xFFF8FAFC)
private val ColorCardBg = Color(0xFFF1F5F9)

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorBg)
            .verticalScroll(rememberScrollState())
    ) {
        ProfileHeader()
        ProfileContent()
    }
}

@Composable
fun ProfileHeader() {
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
                    .background(Color.Gray) // Placeholder for image
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Ricky Rinaldy",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = ColorNavy
            )
        }
        Icon(Icons.Default.MoreVert, contentDescription = null, tint = ColorNavy)
    }
}

@Composable
fun ProfileContent() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                color = Color.Transparent,
                shape = RoundedCornerShape(4.dp),
                border = BorderStroke(1.dp, ColorTeal.copy(alpha = 0.3f))
            ) {
                Text(
                    text = "AVAILABLE FOR PROJECTS",
                    fontSize = 10.sp,
                    color = ColorTeal,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Based in Palembang & West Jakarta, Indonesia",
                fontSize = 10.sp,
                color = Color.DarkGray
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Mobile Engineer (3+ YOE) | ERP, Sales & Business Automation",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = ColorNavy,
            lineHeight = 34.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Improving App Performance & Business Workflows (ERP, Sales Order, Automation)",
            fontSize = 16.sp,
            color = Color(0xFF475569),
            lineHeight = 24.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        AboutSection()

        Spacer(modifier = Modifier.height(32.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = { },
                modifier = Modifier
                    .weight(1.1f)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ColorNavy
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    "View Projects",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    Icons.Default.ArrowOutward,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Button(
                onClick = { },
                modifier = Modifier
                    .weight(1.1f)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ColorCardBg
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    Icons.Default.Download,
                    contentDescription = null,
                    tint = ColorNavy,
                    modifier = Modifier.size(18.dp)
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    "Download CV",
                    color = ColorNavy,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        YOECard()

        Spacer(modifier = Modifier.height(32.dp))

        ExpertiseSection()

        Spacer(modifier = Modifier.height(32.dp))

        TechStackSection()

        Spacer(modifier = Modifier.height(32.dp))

        ExperiencePreviewSection()

        Spacer(modifier = Modifier.height(48.dp))
    }
}

@Composable
fun AboutSection() {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(
                text = "About",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = ColorNavy
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "I'm a Mobile Engineer with over 3 years of experience. Over the years, I've built mobile apps that actually make business operations smoother—mostly focusing on ERP, sales Order management, and Business Automation.\n\nIt's never just about writing code. It's about figuring out how that code solves real bottlenecks for the company.\n\nI specialize in:",
                fontSize = 14.sp,
                color = Color(0xFF64748B),
                lineHeight = 22.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
            val list = listOf(
                "Making apps feel good: " to "Optimizing performance. Whether it's fixing UI lag or implementing lazy-loading for massive datasets, I make sure the internal teams get a seamless experience.",
                "Connecting the dots: " to "Smoothing out complex workflows and data syncs so that daily operations are faster and more reliable.",
                "Hardware integration: " to "Connecting mobile apps with external devices, like thermal printers, and tying them securely to backend services.",
                "AI integration: " to "Leveraging AI features like face recognition and license plate recognition to automate manual processes."
            )
            list.forEach { (bold, normal) ->
                Row(modifier = Modifier.padding(vertical = 4.dp)) {
                    Text("• ", color = ColorNavy, fontWeight = FontWeight.Bold)
                    Text(
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(fontWeight = FontWeight.Bold, color = ColorNavy)) {
                                append(bold)
                            }
                            append(normal)
                        },
                        fontSize = 14.sp,
                        color = Color(0xFF64748B),
                        lineHeight = 20.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "My daily drivers: Flutter, Dart, REST APIs, and Firebase. I'm also developing with Spring Boot and Full-Stack JavaScript.\n\nOpen to opportunities (Remote / Hybrid / Onsite).",
                fontSize = 14.sp,
                color = Color(0xFF64748B),
                lineHeight = 22.sp
            )
        }
    }
}

@Composable
fun YOECard() {
    Card(
        colors = CardDefaults.cardColors(containerColor = ColorCardBg),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(modifier = Modifier.padding(24.dp)) {
            Column {
                Text(
                    text = "3+ YOE",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = ColorNavy
                )
                Text(
                    text = "EXPERTISE IN MOBILE DEVELOPER",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    letterSpacing = 0.5.sp
                )
            }
        }
    }
}

@Composable
fun ExpertiseSection() {
    Column {
        Text(text = "Expertise", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = ColorNavy)
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            ExpertiseItem(
                Icons.Default.Business,
                "ERP Systems",
                Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(12.dp))

            ExpertiseItem(
                Icons.Default.PhoneAndroid,
                "Mobile Apps",
                Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            ExpertiseItem(
                Icons.Default.Dns,
                "Backend",
                Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(12.dp))

            ExpertiseItem(
                Icons.Default.Api,
                "API Integration",
                Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun ExpertiseItem(icon: ImageVector, title: String, modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(containerColor = ColorCardBg),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Icon(icon, contentDescription = null, tint = ColorTeal, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = title, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = ColorNavy)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TechStackSection() {

    val techs = listOf(
        "Flutter",
        "Dart",
        "Kotlin",
        "Java",
        "Spring Boot",
        "SQL",
        "Firebase",
        "REST API",
        "Git",
        "Visual Basic .NET"
    )

    Column {

        Text(
            text = "Tech Stack",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = ColorNavy
        )

        Spacer(modifier = Modifier.height(16.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            techs.forEach { tech ->

                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = Color(0xFFF8FAFC),
                    border = BorderStroke(
                        1.dp,
                        Color(0xFFE2E8F0)
                    ),
                    shadowElevation = 2.dp
                ) {

                    Text(
                        text = tech,
                        modifier = Modifier.padding(
                            horizontal = 16.dp,
                            vertical = 10.dp
                        ),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = ColorNavy
                    )
                }
            }
        }
    }
}

@Composable
fun ExperiencePreviewSection() {
    Column {
        ExperiencePreviewItem(
            status = "PRESENT",
            title = "Freelance Flutter Developer",
            desc = "Focused on building scalable, High-performance mobile applications for business operations, improving performance and data reliability.",
            isFirst = true
        )
        Spacer(modifier = Modifier.height(16.dp))
        ExperiencePreviewItem(
            status = "PREVIOUS",
            title = "Mobile Application Developer — PT. Evo Manufacturing",
            desc = "Developed ERP mobile applications, improved performance through refactoring, and enhanced real-time communication using Firebase.",
            isFirst = false
        )
    }
}

@Composable
fun ExperiencePreviewItem(status: String, title: String, desc: String, isFirst: Boolean) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 4.dp)) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .border(2.dp, if (isFirst) ColorTeal else Color.LightGray, CircleShape)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = status,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = if (isFirst) ColorTeal else Color.Gray,
                letterSpacing = 0.5.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = ColorNavy)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = desc,
                fontSize = 13.sp,
                color = Color(0xFF64748B),
                lineHeight = 20.sp
            )
        }
    }
}
