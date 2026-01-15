package com.example.project_355.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_355.dao.ContactDao
import com.example.project_355.model.Contact
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(private val contactDao: ContactDao) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    fun getAllContacts(): Flow<List<Contact>> {
        return contactDao.getAllContacts()
    }

    fun getFavoriteContacts(): Flow<List<Contact>> {
        return contactDao.getFavoriteContacts()
    }

    // Search functionality
    // This returns a flow that updates whenever the search query changes
    val searchResults: Flow<List<Contact>> = _searchQuery.flatMapLatest { query ->
        if (query.isBlank()) {
            contactDao.getAllContacts()
        } else {
            contactDao.searchContacts(query)
        }
    }
    
    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }
}
