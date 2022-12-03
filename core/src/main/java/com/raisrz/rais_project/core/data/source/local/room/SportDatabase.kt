package com.raisrz.rais_project.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.raisrz.rais_project.core.data.source.local.entity.SportEntity


@Database(entities = [SportEntity::class], version = 1, exportSchema = false)
abstract class SportDatabase : RoomDatabase() {
    abstract fun sportDao(): SportDao
    /*companion object {
        @Volatile
        private var INSTANCE: SportDatabase? = null

        fun getInstance(context: Context): SportDatabase =
            INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                SportDatabase::class.java,
                "Sports.db"
            )
                .fallbackToDestructiveMigration()
                .build()
            INSTANCE = instance
            instance
        }
    }*/
}