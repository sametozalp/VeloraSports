package com.ozalp.velorasports.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun ChooseOrganizationScreen() {
    var selectedTab by remember { mutableStateOf(0) }
    var searchQuery by remember { mutableStateOf("") }
    val items = listOf(
        Organization("Power Gym", "İstanbul • 52 üye", "https://i.pravatar.cc/150?img=1"),
        Organization("Elite Fitness", "Ankara • 34 üye", "https://i.pravatar.cc/150?img=2"),
        Organization("Sparta Club", "İzmir • 18 üye", "https://i.pravatar.cc/150?img=3")
    )

    Scaffold(
        bottomBar = { BottomNavigationBar() },
        containerColor = Color(0xFFF5F5F5) // Gri arkaplan
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFF5F5F5)),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 24.dp)
            ) {
                // 🔥 Logo üstte, kart dışında
                LogoSection()

                Spacer(modifier = Modifier.height(16.dp))

                // 🔥 Kart İçindeki Tüm İçerik
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 20.dp)
                    ) {
                        // Başlıklar
                        Text(
                            text = "Organizasyon Seç",
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Mevcut bir organizasyona katıl veya yeni birine istek gönder.",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(20.dp))

                        // Tab Buttons
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(50))
                                .background(Color(0xFFF3F3F3))
                                .padding(4.dp)
                        ) {
                            TabButton("Listem", selectedTab == 0) { selectedTab = 0 }
                            Spacer(modifier = Modifier.width(8.dp))
                            TabButton("Keşfet", selectedTab == 1) { selectedTab = 1 }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // Search
                        SearchBar(searchQuery) { searchQuery = it }
                        Spacer(modifier = Modifier.height(20.dp))

                        // Organization Cards
                        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            items.filter {
                                it.name.contains(searchQuery, ignoreCase = true)
                            }.forEach { org ->
                                OrganizationItem(org)
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))
                        Divider()
                        Spacer(modifier = Modifier.height(12.dp))

                        TextButton(
                            onClick = {},
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Icon(Icons.Outlined.FitnessCenter, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Yeni Organizasyona Katılma")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LogoSection() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(Color(0xFFFF6600)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_compass),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(28.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "FitManage", fontWeight = FontWeight.Bold, fontSize = 20.sp)
    }
}

@Composable
fun RowScope.TabButton(text: String, selected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) Color(0xFFFF6600) else Color.Transparent,
            contentColor = if (selected) Color.White else Color.Black
        ),
        shape = RoundedCornerShape(50),
        modifier = Modifier
            .weight(1f)
            .height(40.dp),
        elevation = null
    ) {
        Text(text)
    }
}


@Composable
fun SearchBar(value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text("Organizasyon ara") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        shape = RoundedCornerShape(50),
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent
        )
    )
}

@Composable
fun OrganizationItem(org: Organization) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF7F7F7))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(org.imageUrl),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(org.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(org.location, color = Color.Gray, fontSize = 13.sp)
        }
        TextButton(onClick = { }) {
            Text("Seç", color = Color(0xFFFF6600))
        }
    }
}
@Composable
fun BottomNavigationBar() {
    NavigationBar(containerColor = Color.White) {
        NavigationBarItem(
            selected = true,
            onClick = { /* TODO */ },
            icon = { Icon(Icons.Filled.Home, contentDescription = "Organizasyon") },
            label = { Text("Organizasyon") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { /* TODO */ },
            icon = { Icon(Icons.Filled.Notifications, contentDescription = "İstekler") },
            label = { Text("İstekler") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { /* TODO */ },
            icon = { Icon(Icons.Filled.Settings, contentDescription = "Ayarlar") },
            label = { Text("Ayarlar") }
        )
    }
}

data class Organization(
    val name: String,
    val location: String,
    val imageUrl: String
)
