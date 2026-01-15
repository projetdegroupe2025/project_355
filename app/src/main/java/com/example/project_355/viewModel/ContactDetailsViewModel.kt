package com.example.project_355.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_355.dao.ContactDao
import com.example.project_355.model.Contact
import com.example.project_355.model.OperationResponse
import kotlinx.coroutines.launch

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactDetailsViewModel @Inject constructor(private val contactDao: ContactDao) : ViewModel() {

    suspend fun getContact(id: Int): Contact? {
        return contactDao.getContactById(id)
    }

    fun deleteContact(contact: Contact, onResult: (OperationResponse) -> Unit) {
        viewModelScope.launch {
            try {
                contactDao.deleteContact(contact)
                onResult(OperationResponse(isError = false, msg = "Contact deleted successfully"))
            } catch (e: Exception) {
                onResult(OperationResponse(isError = true, msg = "Failed to delete contact: ${e.message}"))
            }
        }
    }

    fun toggleLike(contact: Contact, onResult: (OperationResponse) -> Unit) {
        viewModelScope.launch {
            try {
                val newStatus = !contact.isFavorite
                contactDao.updateFavoriteStatus(contact.contactId, newStatus)
                val msg = if (newStatus) "Added to favorites" else "Removed from favorites"
                onResult(OperationResponse(isError = false, msg = msg))
            } catch (e: Exception) {
                onResult(OperationResponse(isError = true, msg = "Failed to update favorite status: ${e.message}"))
            }
        }
    }

    fun shareContact(contact: Contact): OperationResponse {
        // In a real app, this would probably trigger an Intent.
        // Since ViewModels shouldn't directly handle Intents generally without context, 
        // or we return data to UI to handle it.
        // For now, adhering to the request "return message with proper structures".
        return OperationResponse(isError = false, msg = "Sharing contact: ${contact.firstName} ${contact.phone}")
    }
}
