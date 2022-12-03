package com.raisrz.rais_project.di

import com.raisrz.rais_project.core.domain.usecase.SportInteractor
import com.raisrz.rais_project.core.domain.usecase.SportUsecase
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
    @Binds
    abstract fun provideSportUseCase(sportInteractor: SportInteractor) : SportUsecase
}