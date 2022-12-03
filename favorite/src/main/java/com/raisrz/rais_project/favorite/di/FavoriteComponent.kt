package com.raisrz.rais_project.favorite.di

import com.raisrz.rais_project.di.AppComponent
import com.raisrz.rais_project.favorite.FavoriteActivity
import dagger.Component

@FavoriteScope
@Component(dependencies = [AppComponent::class])
interface FavoriteComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): FavoriteComponent
    }

    fun inject(activity: FavoriteActivity)
}