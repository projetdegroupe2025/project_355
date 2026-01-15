package com.example.project_355.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.ColumnInfo
import androidx.room.Index

@Entity(tableName = "contacts", indices = [Index(value = ["phone"], unique = true)])
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val contactId: Int = 0,
    val firstName: String,
    val secondName: String,
    val phone: String,
    val createdAt: String,
    val lastModified: String,
    val isFavorite: Boolean = false
)

@Entity(
    tableName = "recentContacted",
    foreignKeys = [
        ForeignKey(
            entity = Contact::class,
            parentColumns = ["contactId"],
            childColumns = ["contactId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class RecentContact(
    @PrimaryKey(autoGenerate = true)
    val callId: Int = 0,

    @ColumnInfo(name = "contactId")
    val contactId: Int,

    val date: String
)