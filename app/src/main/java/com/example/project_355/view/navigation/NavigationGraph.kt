package com.example.project_355.view.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.project_355.view.screen.ContactDetailScreen
import com.example.project_355.view.screen.ContactScreen
import com.example.project_355.view.screen.EditContactScreen
import com.example.project_355.view.screen.HomeScreen
import com.example.project_355.view.screen.NewContactScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(BottomNavItem.Contacts.route) {
            ContactScreen(navController = navController)
        }
        composable(BottomNavItem.NewContact.route) {
            NewContactScreen(navController = navController)
        }
        composable(
            route = "contact_detail/{contactId}",
            arguments = listOf(navArgument("contactId") { type = NavType.IntType })
        ) { backStackEntry ->
            val contactId = backStackEntry.arguments?.getInt("contactId") ?: 0
            ContactDetailScreen(contactId = contactId, navController = navController)
        }
        composable(
            route = "edit_contact/{contactId}",
            arguments = listOf(navArgument("contactId") { type = NavType.IntType })
        ) { backStackEntry ->
            val contactId = backStackEntry.arguments?.getInt("contactId") ?: 0
            EditContactScreen(contactId = contactId, navController = navController)
        }
    }
}
