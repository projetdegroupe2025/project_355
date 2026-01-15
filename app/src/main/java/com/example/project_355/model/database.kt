package com.example.project_355.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.project_355.dao.ContactDao

@Database(entities = [Contact::class, RecentContact::class], version = 1)
abstract class Database:RoomDatabase() {
    abstract val dao: ContactDao

    companion object {
        @Volatile
        private var INSTANCE: com.example.project_355.model.Database? = null

        fun getDatabase(context: android.content.Context): com.example.project_355.model.Database {
            return INSTANCE ?: synchronized(this) {
                val instance = androidx.room.Room.databaseBuilder(
                    context.applicationContext,
                    com.example.project_355.model.Database::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

