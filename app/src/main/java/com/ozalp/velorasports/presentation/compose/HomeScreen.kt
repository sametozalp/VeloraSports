package com.ozalp.velorasports.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.ozalp.velorasports.ui.theme.LightGrayBg
import com.ozalp.velorasports.ui.theme.Orange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(
        containerColor = LightGrayBg,
        bottomBar = { BottomNavigationBar2() },
        topBar = {
            TopAppBar(
                title = { Text("Power Gym") },
                navigationIcon = {
                    IconButton(onClick = { /*Back*/ }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(onClick = { /*Profile*/ }) {
                        Icon(Icons.Filled.Person, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            // Profil Kartı
            GymProfileCard()

            Spacer(modifier = Modifier.height(16.dp))

            // Hoşgeldin Mesajı
            WelcomeBanner()

            Spacer(modifier = Modifier.height(16.dp))

            // Kısa Menü
            QuickMenuSection()

            Spacer(modifier = Modifier.height(16.dp))

            // Bugünkü Seanslar
            TodaySessionsSection()

            Spacer(modifier = Modifier.height(16.dp))

            // Antrenörler
            TrainersSection()

            Spacer(modifier = Modifier.height(24.dp))

            // Başla Butonu
            Button(
                onClick = { /* Start Training */ },
                colors = ButtonDefaults.buttonColors(containerColor = Orange),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Antrenmana Başla", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun GymProfileCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter("https://i.pravatar.cc/150?img=1"),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text("Power Gym", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text("İstanbul • 52 üye • Aktif", color = Color.Gray, fontSize = 13.sp)
        }
        Surface(
            color = LightGrayBg,
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .padding(start = 8.dp)
                .border(1.dp, Color.LightGray, RoundedCornerShape(50))
        ) {
            Text(
                "Atlet",
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                fontSize = 13.sp
            )
        }
    }
}

@Composable
fun WelcomeBanner() {
    Card(
        colors = CardDefaults.cardColors(containerColor = LightGrayBg),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            "Hoş geldin! Bugünkü programına göz at ve antrenmanını başlat.",
            color = Color.Black,
            modifier = Modifier.padding(16.dp),
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun QuickMenuSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            QuickMenuItem("Takvimim", Icons.Filled.CalendarToday)
            QuickMenuItem("Programım", Icons.Filled.List)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            QuickMenuItem("İlerleme", Icons.Filled.ShowChart)
            QuickMenuItem("Duyurular", Icons.Filled.Notifications)
        }
    }
}

@Composable
fun QuickMenuItem(
    label: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(150.dp)
            .height(60.dp)
            .clickable { },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Orange,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = label,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}


@Composable
fun TodaySessionsSection() {
    SectionTitle("Bugünkü Seanslar", "Tümünü Gör")

    Spacer(modifier = Modifier.height(8.dp))

    SessionCard(
        title = "Üst Vücut Güç",
        time = "10:30 • A Salon",
        trainer = "Ayşe K."
    )
    Spacer(modifier = Modifier.height(8.dp))
    SessionCard(
        title = "HIIT Kardiyo",
        time = "18:00 • B Salon",
        trainer = "Mehmet Y."
    )
}

@Composable
fun SessionCard(title: String, time: String, trainer: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Filled.FitnessCenter,
                contentDescription = null,
                tint = Orange,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                Text(
                    "$time • Antrenör: $trainer",
                    color = Color.Gray,
                    fontSize = 13.sp
                )
            }
            Icon(Icons.Filled.KeyboardArrowRight, contentDescription = null)
        }
    }
}

@Composable
fun TrainersSection() {
    SectionTitle("Antrenörler", "Hepsini Gör")
    Spacer(modifier = Modifier.height(8.dp))
    TrainerCard(
        name = "Ayşe K.",
        specialty = "Güç & Kondisyon • 4.8"
    )
}

@Composable
fun TrainerCard(name: String, specialty: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter("https://i.pravatar.cc/150?img=5"),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(name, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                Text(specialty, color = Color.Gray, fontSize = 13.sp)
            }
            TextButton(onClick = { }) {
                Text("Mesaj", color = Orange)
            }
        }
    }
}

@Composable
fun SectionTitle(title: String, action: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Text(
            action,
            fontWeight = FontWeight.Bold,
            color = Orange,
            fontSize = 14.sp
        )
    }
}

@Composable
fun BottomNavigationBar2() {
    NavigationBar(containerColor = Color.White) {
        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = { Icon(Icons.Filled.Home, contentDescription = null) },
            label = { Text("Genel") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Filled.CalendarToday, contentDescription = null) },
            label = { Text("Takvim") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Filled.Group, contentDescription = null) },
            label = { Text("Üyeler") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Filled.Settings, contentDescription = null) },
            label = { Text("Ayarlar") }
        )
    }
}
