package com.example.project_355.model

data class Contact(
    var id: Number,
    var firstName: String,
    var secondName: String,
    var phone: String,
    var createdAt: String,
    var lastModified: String,
    var isFavorite: Boolean = false
)

data class RecentContact(
    var contact: Contact,
    var date: String,
)

