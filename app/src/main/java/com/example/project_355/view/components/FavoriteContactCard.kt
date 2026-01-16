package com.example.project_355.view.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project_355.model.Contact

@Composable
fun FavoriteContactCard(contact: Contact, onClick: (()->Unit)? = null, modifier: Modifier = Modifier){
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.padding(10.dp).then(
        if (onClick != null) {
            Modifier.clickable { onClick() }
        } else {
            Modifier
        }
    )) {
        Square(size = 61.dp) {
          Icon(imageVector = Icons.Outlined.Person, contentDescription = null )
        }
        Text(
            text = "${contact.secondName} ${contact.firstName}",
            modifier=Modifier.padding(top = 5.dp),
            fontSize = 11.sp
        )

    }
}