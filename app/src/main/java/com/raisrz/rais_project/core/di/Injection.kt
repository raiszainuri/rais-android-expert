package com.raisrz.rais_project.core.di

import android.content.Context
import com.raisrz.rais_project.core.data.SportRepository
import com.raisrz.rais_project.core.data.source.local.LocalDataSource
import com.raisrz.rais_project.core.data.source.local.room.SportDatabase
import com.raisrz.rais_project.core.data.source.remote.RemoteDataSource
import com.raisrz.rais_project.core.data.source.remote.network.ApiConfig
import com.raisrz.rais_project.core.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): SportRepository {
        val database = SportDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.sportDao())
        val appExecutors = AppExecutors()

        return SportRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}
