package com.example.project_355.view.components
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppBarIcon(label: String, isActive: Boolean = false, activeIcon: ImageVector? = null, Icn: ImageVector){
    if(isActive){
        Box(contentAlignment = Alignment.Center,  modifier = Modifier
            .clip(RoundedCornerShape(50.dp))
            ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
               Box (
                   modifier = Modifier.clip(RoundedCornerShape(50.dp)).background(color = MaterialTheme.colorScheme.tertiary).padding(10.dp)
               ){
                   Icon(
                       imageVector = activeIcon ?: Icn,
                       contentDescription = null,
                       tint= MaterialTheme.colorScheme.primary
                   )
               }
                Text(text = label, color = MaterialTheme.colorScheme.primary,  fontSize = 10.sp)
            }
        }
    }else{
        Box(contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box (modifier = Modifier.clip(RoundedCornerShape(50.dp)).padding(10.dp)){
                    Icon(imageVector = Icn, contentDescription = null)
                }
                Text(text = label, fontSize = 10.sp)
            }
        }
    }

}

