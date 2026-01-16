package com.example.project_355.view.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(var title: String, var icon: ImageVector, var activeIcon: ImageVector, var route: String) {
    object Home : BottomNavItem("Home", Icons.Outlined.Home, Icons.Filled.Home, "home")
    object Contacts : BottomNavItem("Contacts", Icons.Outlined.Phone, Icons.Filled.Phone, "contacts")
    object NewContact : BottomNavItem("Add Contact", Icons.Outlined.Add, Icons.Filled.AddCircle, "new_contact")
}
