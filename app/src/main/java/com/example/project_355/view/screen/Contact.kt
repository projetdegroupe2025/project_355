package com.example.project_355.view.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.project_355.util.initiateCall
import com.example.project_355.view.components.ContactCard
import com.example.project_355.view.components.Group
import com.example.project_355.view.components.Input
import com.example.project_355.viewModel.ContactsViewModel

data class FilterOption(
    var id: Int,
    var label: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreen(navController: androidx.navigation.NavController) {
    val context = LocalContext.current
    val viewModel: ContactsViewModel = hiltViewModel()
    var selectedFilter by remember { mutableStateOf(0) } // 0 = All, 1 = Favorite
    val searchQuery by viewModel.searchQuery.collectAsState()
    val searchResults by viewModel.searchResults.collectAsState(initial = emptyList())
    
    // Filter contacts based on selected filter
    val filteredContacts = if (selectedFilter == 1) {
        searchResults.filter { it.isFavorite }
    } else {
        searchResults
    }
    
    // Group contacts alphabetically by first letter of firstName
    val groupedContacts = filteredContacts
        .sortedBy { it.firstName.uppercase() }
        .groupBy { it.firstName.firstOrNull()?.uppercaseChar() ?: '#' }
    
    val filters = listOf(
        FilterOption(id = 0, label = "All"),
        FilterOption(id = 1, label = "Favorites")
    )
    
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Contacts", fontWeight = FontWeight.Bold) }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ){
            Input(
                onChange = { viewModel.setSearchQuery(it) },
                leadingIcon = Icons.Outlined.Search,
                placeHolder = "Search Contact",
                size = 50.dp
            )

            Spacer(modifier = Modifier.height(20.dp))

            Group(data = filters, spacing = 15.dp) { tab, isActive, onClick ->
                if (isActive) {
                    Button(
                        onClick = {
                            selectedFilter = tab.id
                            onClick()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Blue
                        )
                    ) {
                        Text(text = tab.label, color = Color.White)
                    }
                } else {
                    Button(
                        onClick = {
                            selectedFilter = tab.id
                            onClick()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                        border = BorderStroke(
                            width = 2.dp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(text = tab.label, color = MaterialTheme.colorScheme.primary)
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Contacts list with alphabetical headers
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                groupedContacts.forEach { (initial, contactsForInitial) ->
                    // Alphabetical header
                    item {
                        Text(
                            text = initial.toString(),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }

                    // Contacts under this letter
                    items(contactsForInitial) { contact ->
                        ContactCard(
                            contact = contact,
                            showTime = false,
                            onClick = {
                                navController.navigate("contact_detail/${contact.contactId}")
                            },
                            onCall = {
                                initiateCall(context, contact.phone)
                            }
                        )
                    }
                }
            }
        }
    }
}
