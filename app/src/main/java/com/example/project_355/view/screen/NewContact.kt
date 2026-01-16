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
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.project_355.model.Database
import com.example.project_355.viewModel.NewContactViewModel


import androidx.hilt.navigation.compose.hiltViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true, showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewContactScreen(navController: androidx.navigation.NavController? = null) {
    val context = LocalContext.current
    val viewModel: NewContactViewModel = hiltViewModel()

    var firstName by remember { mutableStateOf("") }
    var secondName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    var r: String  = ""

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "New Contact", fontWeight = FontWeight.Bold) }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text(text="First Name") },
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
                horizontalArrangement = Arrangement.spacedBy(8.dp) // Space between fields
            ) {
                OutlinedTextField(
                    value = "+237",
                    readOnly = true,
                    onValueChange = { },
                    label = { Text("Code") },
                    modifier = Modifier
                        .weight(0.3f) // Takes 30% of row width
                        .padding(bottom = 16.dp),
                    shape = RoundedCornerShape(50)
                )

                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Phone") },
                    modifier = Modifier
                        .weight(0.7f) // Takes 70% of row width
                        .padding(bottom = 16.dp),
                    shape = RoundedCornerShape(50)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = {
                    viewModel.saveContact(firstName, secondName, phone) { response, contactId ->
                        Toast.makeText(context, response.msg, Toast.LENGTH_SHORT).show()
                        if (!response.isError && contactId != null) {
                            // Navigate to contact details
                            navController?.navigate("contact_detail/$contactId")
                        } else if (response.isError) {
                            r = response.msg ?: ""
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Contact")
            }
        }
    }
}