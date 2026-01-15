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
class EditContactViewModel @Inject constructor(private val contactDao: ContactDao) : ViewModel() {

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateContact(contactId: Int, firstName: String, secondName: String, phone: String, isFavorite: Boolean, createdAt: String, onResult: (OperationResponse) -> Unit) {
         if (firstName.isBlank() || phone.isBlank()) {
            onResult(OperationResponse(isError = true, msg = "First name and phone are required"))
            return
        }

        viewModelScope.launch {
            try {
                val currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
                val updatedContact = Contact(
                    contactId = contactId,
                    firstName = firstName,
                    secondName = secondName,
                    phone = phone,
                    createdAt = createdAt, // Keep original creation date
                    lastModified = currentDateTime,
                    isFavorite = isFavorite
                )
                contactDao.upsertContact(updatedContact)
                onResult(OperationResponse(isError = false, msg = "Contact updated successfully"))
            } catch (e: Exception) {
                onResult(OperationResponse(isError = true, msg = "Failed to update contact: ${e.message}"))
            }
        }
    }
}
