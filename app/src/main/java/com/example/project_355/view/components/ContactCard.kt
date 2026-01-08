package com.example.project_355.view.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project_355.model.Contact

@Composable
fun ContactCard(contact: Contact, modifier: Modifier = Modifier, showTime: Boolean = false, onClick: (()->Unit)? = null){
  Row(
      modifier = modifier
          .fillMaxWidth()
          .padding(10.dp)
          .then(
              if (onClick != null) {
                  Modifier.clickable { onClick() }
              } else {
                  Modifier
              }
          ),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
  ){
      Row(
          verticalAlignment = Alignment.CenterVertically,
      ) {
          Square(size = 60.dp) {
              Icon(imageVector = Icons.Outlined.Person, contentDescription =null )
          }
          Column(
              modifier = Modifier.padding(horizontal =  10.dp)
          ) {
              Text(text = "${contact.secondName} ${contact.firstName}", fontWeight = FontWeight.Bold)
              Text(text = contact.phone, color = Color.Gray)
          }
      }

      Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(10.dp)
      ){


          if(showTime){
              Text(text ="13:30 AM", fontSize = 15.sp, color = Color.Gray)
          }

          Square(size = 50.dp, backGroundColor = Color.White, elevation = 20.dp) {
              Icon(imageVector = Icons.Outlined.Phone, contentDescription =null )
          }
      }


  }
}