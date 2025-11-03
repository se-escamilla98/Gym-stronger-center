package com.example.gym_coleman_application.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BackHand
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.SportsGymnastics
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DrawerMenu(
    username: String,
    navController: NavController
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.BottomStart
        ) {
            Text(
                text = "Bienvenido, $username",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.padding(16.dp)
            )
        }
        LazyColumn(modifier = Modifier.weight(1f)) {
            item {
                NavigationDrawerItem(
                    label = { Text("Creatina super coleman") },
                    selected = false,
                    onClick = {
                        navController.navigate("ProductoFormScreen/Creatina super coleman/5000")
                    },
                    icon = { Icon(Icons.Default.SportsGymnastics, contentDescription = "Creatina") },
                    colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.Transparent)
                )
            }
            item {
                NavigationDrawerItem(
                    label = { Text("Pre-entreno Coleman") },
                    selected = false,
                    onClick = {
                        navController.navigate("ProductoFormScreen/Pre-entreno Coleman/7500")
                    },
                    icon = { Icon(Icons.Default.Medication, contentDescription = "Pre-entreno") },
                    colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.Transparent)
                )
            }
            item {
                NavigationDrawerItem(
                    label = { Text("Trembolona") },
                    selected = false,
                    onClick = {
                        navController.navigate("ProductoFormScreen/Trembolona/15000")
                    },
                    icon = { Icon(Icons.Default.Medication, contentDescription = "Esteroides") },
                    colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.Transparent)
                )
            }
            item {
                NavigationDrawerItem(
                    label = { Text("Proteina C-Bomba") },
                    selected = false,
                    onClick = {
                        navController.navigate("ProductoFormScreen/Proteina C-Bomba/9800")
                    },
                    icon = { Icon(Icons.Default.Storefront, contentDescription = "Proteina") },
                    colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.Transparent)
                )
            }
            item {
                NavigationDrawerItem(
                    label = { Text("Straps Stronger") },
                    selected = false,
                    onClick = {
                        navController.navigate("ProductoFormScreen/Straps Stronger/3500")
                    },
                    icon = { Icon(Icons.Default.BackHand, contentDescription = "Straps") },
                    colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.Transparent)
                )
            }
        }
        Text(
            text = "@ 2025 Lightweigth baby.inc",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Center
        )
    }
}
