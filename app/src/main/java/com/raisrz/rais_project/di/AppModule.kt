package com.raisrz.rais_project.di

import com.raisrz.rais_project.core.domain.usecase.SportInteractor
import com.raisrz.rais_project.core.domain.usecase.SportUsecase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {
    @Binds
    @ViewModelScoped
    abstract fun provideSportUseCase(sportInteractor: SportInteractor): SportUsecase
}