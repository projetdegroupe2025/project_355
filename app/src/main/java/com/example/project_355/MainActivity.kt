package com.example.project_355

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.project_355.model.Contact
import com.example.project_355.ui.theme.Project_355Theme
import com.example.project_355.view.components.AppBarIcon
import com.example.project_355.view.components.Btn
import com.example.project_355.view.components.ContactCard
import com.example.project_355.view.components.FavoriteContactCard
import com.example.project_355.view.components.GlassMorphic
import com.example.project_355.view.components.Group
import com.example.project_355.view.components.Input
import com.example.project_355.view.components.Square
import com.example.project_355.view.screen.NewContactScreen

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Project_355Theme {
                // A surface container using the 'background' color from the theme
//                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
//                   // Greeting("Android")
//                   // Test()
//                }
                NewContactScreen()
            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = true  )
@Composable
fun Test() {
    val sampleContact = Contact(
       contactId = 1,
        firstName = "John",
        secondName = "Doe",
        phone = "+1 234 567 8900",
        createdAt = "2024-01-01",
        lastModified = "2024-01-07"
    )

    
    val g:List<Contact> = listOf(
        Contact( contactId = 1,
            firstName = "John",
            secondName = "Doe",
            phone = "+1 234 567 8900",
            createdAt = "2024-01-01",
            lastModified = "2024-01-07"),
        Contact( contactId = 1,
            firstName = "TJay",
            secondName = "Doe",
            phone = "+1 234 567 8900",
            createdAt = "2024-01-01",
            lastModified = "2024-01-07")
    )

    
   Column() {
       FavoriteContactCard(contact = sampleContact)
       ContactCard(contact = sampleContact )
       ContactCard(contact = sampleContact, showTime = false )
       ContactCard(contact = sampleContact , showTime = true)
       //AppBarIcon(label = "Home", Icn = Icons.Outlined.Home )
      // AppBarIcon(label = "Dial", Icn = Icons.Outlined.Call, isActive = true )
       GlassMorphic(elevation = 0.5.dp) {
           AppBarIcon(label = "Home", Icn = Icons.Outlined.Home, isActive = true )
       }

       Group(
           data = g,
           onItemClick = { item, index ->
               println("Clicked: ${item.firstName} at index $index")
               // Navigate to item.route
           }
       ) { item, isActive, onClick ->
          Box(modifier = Modifier.clickable { onClick() }, ){
              Text(
                  text = item.firstName, color = if (isActive) {
                      MaterialTheme.colorScheme.primary
                  } else {
                      Color.Black
                  }
              )
          }
       }


      Icon(imageVector = Icons.Outlined.Home  , contentDescription = null )
       Icon(imageVector = Icons.Filled.Home  , contentDescription = null)


               Box(modifier = Modifier.padding(10.dp)){
          Input(onChange = {}, label = "Name", placeHolder = "Enter name", showLabel = true)
      }

       Box(modifier = Modifier
           .padding(10.dp)
           .fillMaxWidth()){
          Btn(text = "Click", onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth())
       }
   }
}