package com.example.jour.MVVM

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class JourDatabase: RoomDatabase() {
    abstract fun getEntitiesDao(): JourDao

    companion object{

        @Volatile
        private var INSTANCE: JourDatabase?=null

        fun getDatabase(context: Context): JourDatabase {
            return INSTANCE ?: synchronized(this ){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    JourDatabase::class.java,
                    "jour_database"
                ).build()
                INSTANCE =instance
                instance
            }
        }

    }
}