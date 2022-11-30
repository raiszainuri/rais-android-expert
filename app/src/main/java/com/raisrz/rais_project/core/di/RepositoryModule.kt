package com.raisrz.rais_project.core.di

import com.raisrz.rais_project.core.data.SportRepository
import com.raisrz.rais_project.core.domain.repository.ISportRepository
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryModule {
 
    @Binds
    abstract fun provideRepository(sportRepository: SportRepository): ISportRepository
 
}