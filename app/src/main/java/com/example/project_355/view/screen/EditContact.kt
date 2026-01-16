package com.example.project_355.view.screen

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.project_355.model.Contact
import com.example.project_355.viewModel.ContactDetailsViewModel
import com.example.project_355.viewModel.EditContactViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditContactScreen(
    contactId: Int,
    navController: NavController
) {
    val context = LocalContext.current
    val viewModel: EditContactViewModel = hiltViewModel()
    val detailsViewModel: ContactDetailsViewModel = hiltViewModel()
    
    var contact by remember { mutableStateOf<Contact?>(null) }
    var firstName by remember { mutableStateOf("") }
    var secondName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(true) }

    // Load contact data
    LaunchedEffect(contactId) {
        detailsViewModel.getContactById(contactId) { result ->
            contact = result
            result?.let {
                firstName = it.firstName
                secondName = it.secondName
                phone = it.phone
            }
            isLoading = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Edit Contact", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        if (!isLoading && contact != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    label = { Text(text = "First Name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    shape = RoundedCornerShape(50)
                )
                
                Spacer(modifier = Modifier.height(30.dp))
                
                OutlinedTextField(
                    value = secondName,
                    onValueChange = { secondName = it },
                    label = { Text("Second Name (Optional)") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    shape = RoundedCornerShape(50)
                )
                
                Spacer(modifier = Modifier.height(30.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = "+237",
                        readOnly = true,
                        onValueChange = { },
                        label = { Text("Code") },
                        modifier = Modifier
                            .weight(0.3f)
                            .padding(bottom = 16.dp),
                        shape = RoundedCornerShape(50)
                    )

                    OutlinedTextField(
                        value = phone,
                        onValueChange = { phone = it },
                        label = { Text("Phone") },
                        modifier = Modifier
                            .weight(0.7f)
                            .padding(bottom = 16.dp),
                        shape = RoundedCornerShape(50)
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))
                
                Button(
                    onClick = {
                        contact?.let { currentContact ->
                            viewModel.updateContact(
                                contactId = currentContact.contactId,
                                firstName = firstName,
                                secondName = secondName,
                                phone = phone,
                                isFavorite = currentContact.isFavorite,
                                createdAt = currentContact.createdAt
                            ) { response, _ ->
                                Toast.makeText(context, response.msg, Toast.LENGTH_SHORT).show()
                                if (!response.isError) {
                                    navController.popBackStack()
                                }
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Edit Changes")
                }
            }
        }
    }
}
