package com.ricky.rinaldy.cv.features.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import com.ricky.rinaldy.cv.R
import com.ricky.rinaldy.cv.core.theme.CardBg
import com.ricky.rinaldy.cv.core.theme.TealAccent
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

@Composable
fun ProfileScreen() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    fun openFile(file: File) {
        try {
            val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
            val intent = android.content.Intent(android.content.Intent.ACTION_VIEW).apply {
                setDataAndType(uri, "application/pdf")
                addFlags(android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            android.widget.Toast.makeText(context, "No PDF viewer found", android.widget.Toast.LENGTH_SHORT).show()
        }
    }

    fun downloadCV() {
        val fileName = "RickyRinaldy-CV.pdf"
        try {
            val inputStream = context.assets.open("rickyrinaldy_cv.pdf")
            
            // For opening file, we'll save it to a shareable location
            val file = File(context.getExternalFilesDir(android.os.Environment.DIRECTORY_DOWNLOADS), fileName)
            FileOutputStream(file).use { outputStream ->
                inputStream.copyTo(outputStream)
            }

            // Also copy to public Downloads for user convenience (Android 10+)
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                val contentValues = android.content.ContentValues().apply {
                    put(android.provider.MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                    put(android.provider.MediaStore.MediaColumns.MIME_TYPE, "application/pdf")
                    put(android.provider.MediaStore.MediaColumns.RELATIVE_PATH, android.os.Environment.DIRECTORY_DOWNLOADS)
                }
                val contentResolver = context.contentResolver
                val uri = contentResolver.insert(android.provider.MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)
                uri?.let {
                    contentResolver.openOutputStream(it)?.use { output ->
                        context.assets.open("rickyrinaldy_cv.pdf").copyTo(output)
                    }
                }
            }

            scope.launch {
                val result = snackbarHostState.showSnackbar(
                    message = "CV saved to Downloads folder",
                    actionLabel = "OPEN",
                    duration = SnackbarDuration.Long
                )
                if (result == SnackbarResult.ActionPerformed) {
                    openFile(file)
                }
            }
        } catch (e: Exception) {
            android.widget.Toast.makeText(context, "Failed to download CV: ${e.localizedMessage}", android.widget.Toast.LENGTH_LONG).show()
        }
    }

    fun viewProjects() {
        try {
            val intent = android.content.Intent(android.content.Intent.ACTION_VIEW, "https://cv-ricky-rinaldy.vercel.app/".toUri())
            context.startActivity(intent)
        } catch (e: Exception) {
            android.widget.Toast.makeText(context, "Cannot open link", android.widget.Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
        ) {
            ProfileHeader()
            ProfileContent(
                onDownloadCV = { downloadCV() },
                onViewProjects = { viewProjects() }
            )
        }
    }
}

@Composable
fun ProfileHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.pas_foto),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.Gray),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = "Ricky Rinaldy",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun ProfileContent(onDownloadCV: () -> Unit, onViewProjects: () -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                color = Color.Transparent,
                shape = RoundedCornerShape(4.dp),
                border = BorderStroke(1.dp, TealAccent.copy(alpha = 0.3f))
            ) {
                Text(
                    text = "AVAILABLE FOR PROJECTS",
                    fontSize = 10.sp,
                    color = TealAccent,
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
            color = MaterialTheme.colorScheme.primary,
            lineHeight = 34.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Improving App Performance & Business Workflows (ERP, Sales Order, Automation)",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.tertiary,
            lineHeight = 24.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        AboutSection()

        Spacer(modifier = Modifier.height(32.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = onViewProjects,
                modifier = Modifier
                    .weight(1.1f)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
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
                onClick = onDownloadCV,
                modifier = Modifier
                    .weight(1.1f)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = CardBg
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    Icons.Default.Download,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(18.dp)
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    "Download CV",
                    color = MaterialTheme.colorScheme.primary,
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

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), thickness = 1.dp, color = Color(0xFFE2E8F0))

        ExperienceSection()

        HorizontalDivider(modifier = Modifier.padding(vertical = 32.dp), thickness = 1.dp, color = Color(0xFFE2E8F0))

        EducationSection()

        HorizontalDivider(modifier = Modifier.padding(vertical = 32.dp), thickness = 1.dp, color = Color(0xFFE2E8F0))

        AccreditationSection()

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
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "I'm a Mobile Engineer with over 3 years of experience. Over the years, I've built mobile apps that actually make business operations smoother—mostly focusing on ERP, sales Order management, and Business Automation.\n\nIt's never just about writing code. It's about figuring out how that code solves real bottlenecks for the company.\n\nI specialize in:",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.tertiary,
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
                    Text("• ", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                    Text(
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)) {
                                append(bold)
                            }
                            append(normal)
                        },
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.tertiary,
                        lineHeight = 20.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "My daily drivers: Flutter, Dart, REST APIs, and Firebase. I'm also developing with Spring Boot and Full-Stack JavaScript.\n\nOpen to opportunities (Remote / Hybrid / Onsite).",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.tertiary,
                lineHeight = 22.sp
            )
        }
    }
}

@Composable
fun YOECard() {
    Card(
        colors = CardDefaults.cardColors(containerColor = CardBg),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(modifier = Modifier.padding(24.dp)) {
            Column {
                Text(
                    text = "3+ YOE",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
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
        Text(text = "Expertise", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
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
        colors = CardDefaults.cardColors(containerColor = CardBg),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Icon(icon, contentDescription = null, tint = TealAccent, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = title, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.primary)
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
            color = MaterialTheme.colorScheme.primary
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
                    color = MaterialTheme.colorScheme.background,
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
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Composable
fun ExperienceSection() {
    Column(modifier = Modifier.padding(top = 24.dp)) {
        Text(
            text = "Experience.",
            fontSize = 42.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            letterSpacing = (-1).sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Building scalable, high-performance mobile applications and business systems with a focus on modern development practices.",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.tertiary,
            lineHeight = 24.sp
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Freelance
        ExperienceItem(
            date = "OCT 2024 — PRESENT",
            title = "Freelance Flutter Developer",
            company = "Freelance | Self-Employed",
            location = "Remote | Jakarta",
            summary = "Building scalable, high-performance mobile apps for business operations.",
            achievementsTitle = "Key Achievements & Projects:",
            achievements = listOf(
                "Refactored Flutter codebases, reduced UI lag, and increased app stability.",
                "Sales Order Application: Improved inventory tracking and real-time order updates.",
                "Mobile ERP Application: Seamless REST API integration for operational efficiency.",
                "AI & Automation: Face recognition, license plate recognition, and OCR using ML Kit & TensorFlow Lite."
            ),
            tags = listOf("FLUTTER", "DART", "REST API", "FIREBASE", "ML KIT", "TENSORFLOW LITE")
        )

        Spacer(modifier = Modifier.height(48.dp))

        // PT. Evo
        ExperienceItem(
            date = "TOTAL: 3 YEARS 7 MONTHS",
            title = "PT. Evo Manufacturing Indonesia",
            roles = listOf(
                "Mobile Application Developer" to "Nov 2021 — Present | Palembang, Indonesia",
                "Software Developer" to "Feb 2021 — Oct 2021 | Palembang, Indonesia",
                "Flutter Developer" to "Dec 2022 — Feb 2023 | Palembang, Indonesia"
            ),
            summary = "Focused on mobile and desktop apps for internal business systems.",
            achievementsTitle = "Achievements & Responsibilities:",
            achievements = listOf(
                "Developed and optimized ERP mobile apps with Flutter for better performance.",
                "Built Sales Order Machine (SIMA) and Reporting Dashboards.",
                "Flutter Developer Role: Built Core System features and UI components for all applications.",
                "Desktop Adaptability: Developed .NET Web, Server, Desktop apps and Crystal Reports."
            ),
            tags = listOf("FLUTTER", "DART", "FIREBASE", "VB.NET", "SQL SERVER", "SQL", "CRYSTAL REPORTS")
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Skylar
        ExperienceItem(
            date = "OCT 2019 — MAR 2021",
            title = "Website Builder | IT Support",
            company = "Skylar Digital Creative",
            location = "Palembang, Indonesia",
            summary = "Data Loader and Content Creator for Amazon Affiliate Marketing (Sandbox 7 Support), Internal Research, and website building using Reality, Divi, and PHP storm.",
            tags = listOf("DIVI", "WPML", "SANDBOX 7", "PHP STORM")
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Prakasa Utama
        ExperienceItem(
            date = "AUG 2018",
            title = "Backup Engineer | IT Support",
            company = "PT. Prakasa Utama",
            location = "Palembang, Indonesia",
            summary = "Managed network maintenance and and server checking services. Assisted in hardware and software solutions for system support and communication."
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ExperienceItem(
    date: String,
    title: String,
    company: String? = null,
    location: String? = null,
    roles: List<Pair<String, String>>? = null,
    summary: String? = null,
    achievementsTitle: String? = null,
    achievements: List<String>? = null,
    tags: List<String>? = null
) {
    Column {
        Text(
            text = date,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = TealAccent,
            letterSpacing = 0.5.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            lineHeight = 30.sp
        )

        if (company != null) {
            Text(
                text = company,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                fontWeight = FontWeight.Medium
            )
        }

        if (location != null) {
            Text(
                text = location,
                fontSize = 13.sp,
                color = Color.Gray
            )
        }

        roles?.forEach { (role, duration) ->
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = role,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = duration,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                if (summary != null) {
                    Text(
                        text = summary,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.tertiary,
                        lineHeight = 22.sp
                    )
                }

                if (achievementsTitle != null) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = achievementsTitle,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }

                achievements?.forEach { achievement ->
                    Row(modifier = Modifier.padding(vertical = 6.dp)) {
                        Icon(
                            Icons.Default.RadioButtonUnchecked,
                            contentDescription = null,
                            tint = Color.LightGray,
                            modifier = Modifier
                                .size(14.dp)
                                .padding(top = 4.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = achievement,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.tertiary,
                            lineHeight = 20.sp
                        )
                    }
                }

                if (!tags.isNullOrEmpty()) {
                    Spacer(modifier = Modifier.height(20.dp))
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        tags.forEach { tag ->
                            Surface(
                                color = CardBg,
                                shape = RoundedCornerShape(6.dp),
                                border = BorderStroke(1.dp, Color(0xFFE2E8F0))
                            ) {
                                Text(
                                    text = tag,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TealAccent
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EducationSection() {
    Column {
        Text(
            text = "Education",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Academic Foundation",
            fontSize = 14.sp,
            color = TealAccent,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(56.dp),
                color = CardBg,
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(
                    Icons.Default.School,
                    contentDescription = null,
                    tint = TealAccent,
                    modifier = Modifier.padding(14.dp)
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Text(
                    text = "ASSOCIATE'S DEGREE",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    letterSpacing = 0.5.sp
                )
                Text(
                    text = "Computer Science",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Politeknik Sekayu",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    text = "2015 — 2018",
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun AccreditationSection() {
    Column {
        Text(
            text = "Professional Accreditation",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            AccreditationItem(
                title = "IT Essentials",
                icon = Icons.Default.VerifiedUser,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(12.dp))
            AccreditationItem(
                title = "Fullstack JavaScript Bootcamp",
                icon = Icons.Default.Code,
                isDark = true,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            AccreditationItem(
                title = "Spring Boot Back-End Engineering Bootcamp",
                icon = Icons.Default.Terminal,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun AccreditationItem(
    title: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    isDark: Boolean = false
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (isDark) MaterialTheme.colorScheme.primary else CardBg
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier.height(160.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                icon,
                contentDescription = null,
                tint = if (isDark) Color.White else TealAccent,
                modifier = Modifier.size(28.dp)
            )
            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = if (isDark) Color.White else MaterialTheme.colorScheme.primary,
                lineHeight = 22.sp
            )
        }
    }
}
