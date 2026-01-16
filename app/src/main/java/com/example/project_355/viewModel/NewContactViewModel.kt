package com.example.project_355.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_355.dao.ContactDao
import com.example.project_355.model.Contact
import com.example.project_355.model.OperationResponse
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewContactViewModel @Inject constructor(private val contactDao: ContactDao) : ViewModel() {

    @RequiresApi(Build.VERSION_CODES.O)
    fun saveContact(firstName: String, secondName: String, phone: String, onResult: (OperationResponse, Int?) -> Unit) {
        if (firstName.isBlank() || phone.isBlank()) {
            onResult(OperationResponse(isError = true, msg = "First name and phone are required"), null)
            return
        }

        viewModelScope.launch {
            try {
                val currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
                val newContact = Contact(
                    firstName = firstName,
                    secondName = secondName,
                    phone = phone,
                    createdAt = currentDateTime,
                    lastModified = currentDateTime,
                    isFavorite = false
                )
                val contactId = contactDao.upsertContact(newContact)
                onResult(OperationResponse(isError = false, msg = "Contact saved successfully"), contactId.toInt())
            } catch (e: Exception) {
                onResult(OperationResponse(isError = true, msg = "Failed to save contact: ${e.message}"), null)
            }
        }
    }
}
