package com.raisrz.rais_project.core.di

import android.content.Context
import androidx.room.Room
import com.raisrz.rais_project.core.data.source.local.room.SportDao
import com.raisrz.rais_project.core.data.source.local.room.SportDatabase
import dagger.Module
import dagger.Provides
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
class DatabaseModule {
    private val passKey: ByteArray = SQLiteDatabase.getBytes("raisznu".toCharArray())
    private val factory = SupportFactory(passKey)

    @Singleton
    @Provides
    fun provideDatabase(context: Context): SportDatabase = Room.databaseBuilder(
        context,
        SportDatabase::class.java, "Sports.db"
    ).fallbackToDestructiveMigration()
        .openHelperFactory(factory)
        .build()

    @Provides
    fun provideSportDao(database: SportDatabase): SportDao = database.sportDao()
}