package com.example.project_355.view.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.project_355.model.Contact
import com.example.project_355.util.initiateCall
import com.example.project_355.view.components.ContactCard
import com.example.project_355.view.components.FavoriteContactCard
import com.example.project_355.viewModel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: androidx.navigation.NavController? = null) {
    var viewModel: HomeViewModel = hiltViewModel()
    var res: List<Contact> = listOf<Contact>(
        Contact(
            contactId = 1,
            firstName = "Tsem",
            secondName = "Idriss",
            phone = "679041837",
            createdAt = "",
            lastModified = "",
            isFavorite = false
        ),
        Contact(
            contactId = 1,
            firstName = "Tsem",
            secondName = "Idriss",
            phone = "679041837",
            createdAt = "",
            lastModified = "",
            isFavorite = false
        ),
        Contact(
            contactId = 1,
            firstName = "Tsem",
            secondName = "Idriss",
            phone = "679041837",
            createdAt = "",
            lastModified = "",
            isFavorite = false
        ), Contact(
            contactId = 1,
            firstName = "Tsem",
            secondName = "Idriss",
            phone = "679041837",
            createdAt = "",
            lastModified = "",
            isFavorite = false
        ), Contact(
            contactId = 1,
            firstName = "Tsem",
            secondName = "Idriss",
            phone = "679041837",
            createdAt = "",
            lastModified = "",
            isFavorite = false
        ),Contact(
            contactId = 1,
            firstName = "Tsem",
            secondName = "Idriss",
            phone = "679041837",
            createdAt = "",
            lastModified = "",
            isFavorite = false
        ),Contact(
            contactId = 1,
            firstName = "Tsem",
            secondName = "Idriss",
            phone = "679041837",
            createdAt = "",
            lastModified = "",
            isFavorite = false
        ),
    )
    val favoriteContacts by viewModel.getFavoriteContacts().collectAsState(initial = emptyList())
    val context = LocalContext.current

    //val recentContacts by viewModel.getRecentContacts().collectAsState(initial = emptyList())

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Home", fontWeight = FontWeight.Bold) }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)

        ){
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(27.dp),
            ){
                items(favoriteContacts){
                    FavoriteContactCard(
                        contact = it,
                        onClick = {
                            navController?.navigate("contact_detail/${it.contactId}")
                        }
                    )
                }
            }
            
           Column(modifier = Modifier.padding(horizontal = 16.dp)) {
               Text(
                   text = "Recent",
                   fontSize = 16.sp,
                   fontWeight = FontWeight.Bold,
                   modifier = Modifier.padding(vertical = 10.dp)
               )
               LazyColumn(){
                   items(res){
                       ContactCard(
                           contact = it,
                           showTime = true,
                           onClick = {
                               navController?.navigate("contact_detail/${it.contactId}")
                           },
                           onCall = {
                               initiateCall(context, it.phone)
                           }
                       )
                   }
               }
           }
        }
    }
}
