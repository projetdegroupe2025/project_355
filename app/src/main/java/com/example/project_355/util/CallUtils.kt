package com.example.project_355.util

import android.content.Context
import android.content.Intent
import android.net.Uri


fun initiateCall(context: Context, phoneNumber: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$phoneNumber")
    }
    context.startActivity(intent)
}
