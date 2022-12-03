package com.raisrz.rais_project.core.di

import android.content.Context
import androidx.room.Room
import com.raisrz.rais_project.core.data.source.local.room.SportDao
import com.raisrz.rais_project.core.data.source.local.room.SportDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
   @Singleton
   @Provides
   fun provideDatabase(context: Context): SportDatabase = Room.databaseBuilder(
       context,
       SportDatabase::class.java, "Sports.db"
   ).fallbackToDestructiveMigration().build()
 
   @Provides
   fun provideSportDao(database: SportDatabase): SportDao = database.sportDao()
}