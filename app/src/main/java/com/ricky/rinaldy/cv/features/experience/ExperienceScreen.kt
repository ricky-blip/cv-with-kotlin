package com.ricky.rinaldy.cv.features.experience

import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ricky.rinaldy.cv.R

private val ColorNavy = Color(0xFF10172A)
private val ColorTeal = Color(0xFF0D9488)
private val ColorBg = Color(0xFFF8FAFC)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ExperienceScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorBg)
            .verticalScroll(rememberScrollState())
    ) {
        ExperienceHeader()
        
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Experience.",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = ColorNavy
            )
            Text(
                text = "Building scalable, high-performance mobile applications and business systems with a focus on modern development practices.",
                fontSize = 14.sp,
                color = Color(0xFF64748B),
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            // --- Freelance ---
            DetailedExperienceItem(
                period = "JAN 2023 — PRESENT",
                role = "Freelance Flutter Developer",
                company = "Freelance | Self-Employed",
                location = "Jakarta, Indonesia",
                summary = "Building scalable, high-performance mobile apps for business operations.",
                projectsTitle = "Key Achievements & Projects:",
                projects = listOf(
                    ProjectDetail("Refactored Flutter codebases", "Reduced UI lag and increased app stability."),
                    ProjectDetail("Sales Order Applications", "Improved memory loading and real-time synchronization."),
                    ProjectDetail("Mobile ERP Application", "Seamless REST API integration for operational workflows."),
                    ProjectDetail("Print Automation", "Printer integration handling receipts, and label using ESC/POS & Zebra Print Lib.")
                ),
                techStack = listOf("FLUTTER", "DART", "FIREBASE", "ML KIT", "REST API","TENSORFLOW", "DESIGN PATTERNS")
            )

            Spacer(modifier = Modifier.height(48.dp))

            // --- PT Evo ---
            DetailedExperienceItem(
                period = "TOTAL: 3 YEARS 7 MONTHS",
                role = "PT. Evo Manufacturing Indonesia",
                company = "",
                subRoles = listOf(
                    SubRole("Mobile Application Developer", "May 2019 — Present (Freelance)"),
                    SubRole("Software Developer", "Feb 2017 — Apr 2019 (Palembang, Indonesia)"),
                    SubRole("Flutter Developer", "Sep 2021 — Sep 2022 (Part time)")
                ),
                summary = "Focused on mobile and desktop apps for internal business systems.",
                projectsTitle = "Achievements & Responsibilities:",
                projects = listOf(
                    ProjectDetail("Developed and optimized ERP mobile apps", "With Firebase real-time syncs & FCM."),
                    ProjectDetail("Built Sales Order, Machine Control", "And reporting dashboards."),
                    ProjectDetail("Flutter Developer Role", "Built Sales Order mobile with API consumption and UI optimization."),
                    ProjectDetail("Dashboards & Reporting", "Developed desktop apps for internal data analysis and reporting.")
                ),
                techStack = listOf("FLUTTER", "DART", "FIREBASE", "SQL SERVER", "VB.NET", "CRYSTAL REPORTS", "VISUAL BASIC 6")
            )

            Spacer(modifier = Modifier.height(48.dp))

            // --- Skylar ---
            DetailedExperienceItem(
                period = "OCT 2015 — SEP 2017",
                role = "Website Builder | IT Support",
                company = "Skylar Digital Creative",
                location = "Palembang, Indonesia",
                summary = "Team leader and Content Creator for Amazon affiliate marketing. Handled IT Support, Internet Research, and website building using Joomla, Hugo, and WP themes.",
                techStack = listOf("JOOMLA", "WP", "AMAZON AFFILIATE", "HUGO", "Vultr", "VNC Viewer", "IT SUPPORT")
            )

            Spacer(modifier = Modifier.height(48.dp))

            // --- Trakindo ---
            DetailedExperienceItem(
                period = "OCT 2015",
                role = "Backup Engineer | IT Support",
                company = "PT Trakindo Utama (Trakindo)",
                location = "Palembang, Indonesia",
                summary = "Managed network maintenance and client visiting services. Utilized MikroTik and Microsoft Outlook for system support and communication.",
                techStack = emptyList()
            )

            Spacer(modifier = Modifier.height(48.dp))

            // --- EDUCATION ---
            Text(text = "Education", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = ColorNavy)
            Text(text = "ACADEMIC FOUNDATION", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = ColorTeal)
            
            Spacer(modifier = Modifier.height(24.dp))
            
            EducationItem(
                major = "Computer Science",
                school = "Politeknik Sekayu",
                period = "2015 — 2018",
                location = "Musi Banyuasin, Indonesia"
            )

            Spacer(modifier = Modifier.height(48.dp))

            // ---------- PROFESSIONAL ACCREDITATION ----------

            Text(
                text = "Professional Accreditation",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = ColorNavy
            )

            Spacer(modifier = Modifier.height(24.dp))

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                maxItemsInEachRow = 2
            ) {
                AccreditationItem(
                    icon = Icons.Default.Code,
                    title = "Backend Javascript",
                    subtitle = "Bootcamp",
                    isDark = true
                )

                AccreditationItem(
                    icon = Icons.Default.Storage,
                    title = "Spring Boot",
                    subtitle = "Backend Engineering",
                    isDark = false
                )

                AccreditationItem(
                    icon = Icons.Default.Cloud,
                    title = "IT Essentials",
                    subtitle = "Cisco Networking Academy",
                    isDark = false
                )


            }

            Spacer(modifier = Modifier.height(64.dp))
            
            Spacer(modifier = Modifier.height(64.dp))
        }
    }
}

@Composable
fun ExperienceHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
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
            Text(text = "Ricky Rinaldy", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = ColorNavy)
        }
        Icon(Icons.Default.MoreVert, contentDescription = null, tint = ColorNavy)
    }
}

data class ProjectDetail(val title: String, val desc: String)
data class SubRole(val title: String, val period: String)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DetailedExperienceItem(
    period: String,
    role: String,
    company: String,
    location: String? = null,
    subRoles: List<SubRole> = emptyList(),
    summary: String? = null,
    projectsTitle: String? = null,
    projects: List<ProjectDetail> = emptyList(),
    techStack: List<String> = emptyList()
) {
    Column {
        Text(text = period, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = ColorTeal)
        Text(text = role, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = ColorNavy, lineHeight = 30.sp)
        if (company.isNotEmpty()) {
            Text(text = company, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF475569))
        }
        if (location != null) {
            Text(text = location, fontSize = 12.sp, color = Color.Gray)
        }
        
        if (subRoles.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            subRoles.forEach { subRole ->
                Column(modifier = Modifier.padding(vertical = 4.dp)) {
                    Text(text = subRole.title, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = ColorNavy)
                    Text(text = subRole.period, fontSize = 12.sp, color = Color.Gray)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                if (summary != null) {
                    Text(
                        text = summary,
                        fontSize = 14.sp,
                        color = Color(0xFF64748B),
                        lineHeight = 22.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }

                if (projects.isNotEmpty()) {
                    Text(
                        text = projectsTitle ?: "Key Projects:",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = ColorNavy
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    projects.forEach { project ->
                        Row(modifier = Modifier.padding(bottom = 8.dp)) {
                            Box(
                                modifier = Modifier
                                    .padding(top = 6.dp)
                                    .size(6.dp)
                                    .border(1.dp, Color.Gray, CircleShape)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                if (project.title.isNotEmpty()) {
                                    Text(
                                        text = project.title + ": " + project.desc,
                                        fontSize = 13.sp,
                                        color = Color(0xFF64748B),
                                        lineHeight = 18.sp
                                    )
                                }
                            }
                        }
                    }
                }

                if (techStack.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(12.dp))
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        techStack.forEach { tech ->
                            Surface(
                                color = Color(0xFFDBEAFE).copy(alpha = 0.7f),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(
                                    text = tech,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF1E40AF)
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
fun EducationItem(major: String, school: String, period: String, location: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(Color(0xFFE0F2FE), RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.School, contentDescription = null, tint = ColorTeal, modifier = Modifier.size(24.dp))
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = "ASSOCIATE'S DEGREE".uppercase(), fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
            Text(text = major, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = ColorNavy)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = school, fontSize = 14.sp, color = Color(0xFF475569))
            Text(text = "$location • $period", fontSize = 12.sp, color = Color.Gray)
        }
    }
}

@Composable
fun AccreditationItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    isDark: Boolean
) {

    val background = if (isDark) ColorNavy else Color.White

    val textColor = if (isDark) Color.White else ColorNavy

    Card(
        modifier = Modifier
            .width(170.dp)
            .aspectRatio(1.2f),

        shape = RoundedCornerShape(20.dp),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        ),

        colors = CardDefaults.cardColors(
            containerColor = background
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp),

            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column {

                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(
                            if (isDark)
                                Color(0xFF1E3A5F)
                            else
                                Color(0xFFE0F2FE)
                        ),

                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = icon,
                        contentDescription = null,

                        tint =
                            if (isDark)
                                Color(0xFF38BDF8)
                            else
                                ColorTeal,

                        modifier = Modifier.size(22.dp)
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = title,

                    fontSize = 15.sp,

                    fontWeight = FontWeight.Bold,

                    color = textColor
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = subtitle,

                    fontSize = 12.sp,

                    color =
                        if (isDark)
                            Color.LightGray
                        else
                            Color.Gray
                )
            }

            AssistChip(
                onClick = {},

                enabled = false,

                label = {
                    Text(
                        text = "Bootcamp",
                        fontSize = 11.sp
                    )
                }
            )
        }
    }
}
