package com.example.project_355.view.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Input(
    label: String? = null,
    size: Dp = 10.dp,
    value: String = "",
    onChange: (String) -> Unit,
    placeHolder: String? = "",
    showLabel: Boolean = false,
    modifier: Modifier = Modifier,
    trailingIcon: ImageVector? = null,
    leadingIcon: ImageVector? = null
){
    Column(modifier = modifier) {
        if(showLabel && label != null){
            Text(text = label, modifier = Modifier.padding(bottom = 4.dp))
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.LightGray, RoundedCornerShape(size))
                .padding(horizontal = 16.dp, vertical = 14.dp)
        ){
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                if(leadingIcon != null){
                    Icon(imageVector = leadingIcon, contentDescription = null)
                }

                Box(modifier = Modifier.weight(1f)) {
                    if (value.isEmpty() && !placeHolder.isNullOrEmpty()) {
                        Text(
                            text = placeHolder,
                            color = Color.Gray.copy(alpha = 0.5f)
                        )
                    }
                    BasicTextField(
                        value = value,
                        onValueChange = onChange,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                if(trailingIcon != null){
                    Icon(imageVector = trailingIcon, contentDescription = null)
                }
            }
        }
    }
}