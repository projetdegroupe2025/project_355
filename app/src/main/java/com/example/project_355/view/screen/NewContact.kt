package com.example.project_355.view.screen

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
fun NewContactScreen() {
    val context = LocalContext.current
    val viewModel: NewContactViewModel = hiltViewModel()

    var firstName by remember { mutableStateOf("") }
    var secondName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    var r: String  = ""

    Scaffold(
        topBar = { TopAppBar(title = { Text("New Contact") }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            Text(text = r)
            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("First Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = secondName,
                onValueChange = { secondName = it },
                label = { Text("Second Name (Optional)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Phone") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            Button(
                onClick = {
                    viewModel.saveContact(firstName, secondName, phone) { response ->
                        Toast.makeText(context, response.msg, Toast.LENGTH_SHORT).show()
                        if (!response.isError) {
                            // Navigate back or clear fields
                            firstName = ""
                            secondName = ""
                            phone = ""
                        }else{
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