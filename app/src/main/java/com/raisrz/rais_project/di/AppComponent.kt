package com.raisrz.rais_project.di

import com.raisrz.rais_project.core.di.AppScope
import com.raisrz.rais_project.core.di.CoreComponent
import com.raisrz.rais_project.core.domain.usecase.SportUsecase
import com.raisrz.rais_project.ui.detail.DetailActivity
import com.raisrz.rais_project.ui.main.MainActivity
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun provideUseCase() : SportUsecase
    fun inject(activity: MainActivity)
    fun inject(activity: DetailActivity)
}