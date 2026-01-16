package com.example.project_355.dao

import androidx.room.*
import com.example.project_355.model.Contact
import com.example.project_355.model.RecentContact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    /*
      ***  Contacts operations
    */


    @Upsert
    suspend fun upsertContact(contact: Contact): Long

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("SELECT * FROM contacts ORDER BY firstName ASC")
    fun getAllContacts(): Flow<List<Contact>>

    @Query("SELECT * FROM contacts WHERE contactId = :id")
    suspend fun getContactById(id: Int): Contact?

    @Query("SELECT * FROM contacts WHERE isFavorite = 1 ORDER BY firstName ASC")
    fun getFavoriteContacts(): Flow<List<Contact>>

    @Query("UPDATE contacts SET isFavorite = :isFavorite WHERE contactId = :contactId")
    suspend fun updateFavoriteStatus(contactId: Int, isFavorite: Boolean)

    @Query("SELECT * FROM contacts WHERE firstName LIKE '%' || :query || '%' OR secondName LIKE '%' || :query || '%' OR phone LIKE '%' || :query || '%'")
    fun searchContacts(query: String): Flow<List<Contact>>

    @Query("DELETE FROM contacts")
    suspend fun deleteAllContacts()


    /*
      ***  RECENT CONTACT OPERATIONS
     */

    @Insert
    suspend fun insertRecentContact(rec: RecentContact)

    @Delete
    suspend fun deleteRecentContact(rec: RecentContact)

    @Query("SELECT * FROM recentContacted ORDER BY date DESC LIMIT 20")
    fun getRecentContacts(): Flow<List<RecentContact>>

    @Query("DELETE FROM recentContacted")
    suspend fun clearRecentContacts()
}