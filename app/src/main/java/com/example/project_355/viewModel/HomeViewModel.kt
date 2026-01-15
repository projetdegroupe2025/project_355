package com.example.project_355.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_355.dao.ContactDao
import com.example.project_355.model.Contact
import com.example.project_355.model.RecentContact
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val contactDao: ContactDao) : ViewModel() {

    // Get 5 favorite contacts (limiting to 5 as per requirement "get five favorite")
    fun getFavoriteContacts(): Flow<List<Contact>> {
        return contactDao.getFavoriteContacts().map { list ->
            list.take(5)
        }
    }

    // Get recent contacted
    fun getRecentContacts(): Flow<List<RecentContact>> {
        return contactDao.getRecentContacts()
    }
}
