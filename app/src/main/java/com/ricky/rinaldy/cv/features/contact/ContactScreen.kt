package com.ricky.rinaldy.cv.features.contact

import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import com.ricky.rinaldy.cv.R
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

private val ColorNavy = Color(0xFF10172A)
private val ColorTeal = Color(0xFF0D9488)
private val ColorBg = Color(0xFFF8FAFC)
private val ColorCardBg = Color(0xFFF1F5F9)

@Composable
fun ContactScreen() {
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

    fun openUrl(url: String) {
        try {
            val intent = android.content.Intent(android.content.Intent.ACTION_VIEW, url.toUri())
            context.startActivity(intent)
        } catch (e: Exception) {
            android.widget.Toast.makeText(context, "Cannot open link", android.widget.Toast.LENGTH_SHORT).show()
        }
    }

    fun sendEmail(name: String = "", message: String = "") {
        try {
            val subject = if (name.isNotEmpty()) "Inquiry from CV App - $name" else "Inquiry from CV App"
            val body = if (message.isNotEmpty()) "Message:\n$message" else ""
            
            val uriString = "mailto:ricky.rinaldy77@gmail.com" +
                    "?subject=${android.net.Uri.encode(subject)}" +
                    "&body=${android.net.Uri.encode(body)}"
            
            val intent = android.content.Intent(android.content.Intent.ACTION_SENDTO).apply {
                data = android.net.Uri.parse(uriString)
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            android.widget.Toast.makeText(context, "No email app found", android.widget.Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.White)
                .verticalScroll(rememberScrollState())
        ) {
            ContactHeader()
            
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = "GET IN TOUCH",
                    fontSize = 11.sp,
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

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "CONNECT EVERYWHERE",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    letterSpacing = 0.5.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                
                // Connect Grid
                Row(modifier = Modifier.fillMaxWidth()) {
                    ContactCard(
                        icon = Icons.Default.Code,
                        title = "GitHub",
                        desc = "Code",
                        iconBgColor = ColorNavy,
                        iconTint = Color.White,
                        modifier = Modifier.weight(1f),
                        onClick = { openUrl("https://github.com/ricky-blip") }
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    ContactCard(
                        icon = Icons.Default.Description,
                        title = "Medium",
                        desc = "Writing",
                        iconBgColor = Color(0xFFF1F5F9),
                        iconTint = ColorNavy,
                        modifier = Modifier.weight(1f),
                        onClick = { openUrl("https://medium.com/@R2Develop") }
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    ContactCard(
                        icon = Icons.Default.Share,
                        title = "LinkedIn",
                        desc = "Network",
                        iconBgColor = Color(0xFFE0F2FE),
                        iconTint = Color(0xFF0284C7),
                        modifier = Modifier.weight(1f),
                        onClick = { openUrl("https://www.linkedin.com/in/rickyrinaldy/") }
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    ContactCard(
                        icon = Icons.Default.Email,
                        title = "Email",
                        desc = "Direct",
                        iconBgColor = Color(0xFFE0F2FE),
                        iconTint = ColorTeal,
                        modifier = Modifier.weight(1f),
                        onClick = { sendEmail() }
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))
            }

            // Section with gray background
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(ColorBg)
                    .padding(horizontal = 16.dp)
            ) {
                HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f))
                Spacer(modifier = Modifier.height(48.dp))

                ContactForm(onSendMessage = { name, msg -> sendEmail(name, msg) })

                Spacer(modifier = Modifier.height(32.dp))

                ResumeDownloadCard(onDownloadClick = { downloadCV() })

                Spacer(modifier = Modifier.height(32.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Info, 
                        contentDescription = null, 
                        tint = Color(0xFF38BDF8), 
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
                
                Spacer(modifier = Modifier.height(32.dp))
                
                LanguagesSection()

                Spacer(modifier = Modifier.height(48.dp))
            }
        }
    }
}

@Composable
fun ContactHeader() {
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
        Text(text = "Ricky Rinaldy", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = ColorNavy)
    }
}

@Composable
fun ContactCard(
    icon: ImageVector, 
    title: String, 
    desc: String,
    iconBgColor: Color,
    iconTint: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(iconBgColor, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = iconTint, modifier = Modifier.size(18.dp))
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = title, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = ColorNavy)
                Text(text = desc, fontSize = 10.sp, color = Color.Gray)
            }
        }
    }
}

@Composable
fun ContactForm(onSendMessage: (String, String) -> Unit) {
    var name by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(ColorCardBg.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
            .padding(24.dp)
    ) {
        FormField(
            label = "YOUR NAME",
            placeholder = "John Cena",
            value = name, 
            onValueChange = { name = it }
        )
        Spacer(modifier = Modifier.height(20.dp))
        FormField(
            label = "MESSAGE", 
            placeholder = "Hi, I'm a user of your app.",
            isLarge = true, 
            value = message, 
            onValueChange = { message = it }
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = { onSendMessage(name, message) },
            enabled = name.isNotEmpty() && message.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color(0xFF38BDF8),
                disabledContainerColor = ColorNavy.copy(alpha = 0.5f),
                disabledContentColor = Color.White.copy(alpha = 0.7f)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Send Message",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                Icons.AutoMirrored.Filled.Send,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
fun FormField(
    label: String, 
    placeholder: String, 
    value: String,
    onValueChange: (String) -> Unit,
    isLarge: Boolean = false
) {
    Column {
        Text(text = label, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.Gray, letterSpacing = 0.5.sp)
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(placeholder, fontSize = 14.sp, color = Color.Gray) },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
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
fun ResumeDownloadCard(onDownloadClick: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE2E8F0).copy(alpha = 0.5f)),
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
            TextButton(onClick = onDownloadClick, contentPadding = PaddingValues(0.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Download CV (PDF)", color = ColorTeal, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(Icons.Default.Download, contentDescription = null, tint = ColorTeal, modifier = Modifier.size(20.dp))
                }
            }
        }
    }
}

@Composable
fun LanguagesSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(ColorCardBg.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
            .padding(24.dp)
    ) {
        Text(
            text = "LANGUAGES",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            letterSpacing = 0.5.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            LanguageChip("Bahasa Inggris")
            Spacer(modifier = Modifier.width(8.dp))
            LanguageChip("Bahasa Indonesia")
        }
    }
}

@Composable
fun LanguageChip(label: String) {
    Surface(
        color = Color.Gray.copy(alpha = 0.15f),
        shape = CircleShape
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = ColorNavy
        )
    }
}
