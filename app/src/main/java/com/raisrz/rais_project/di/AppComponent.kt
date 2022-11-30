package com.raisrz.rais_project.di

import com.raisrz.rais_project.core.di.CoreComponent
import com.raisrz.rais_project.detail.DetailActivity
import com.raisrz.rais_project.favorite.FavoriteActivity
import com.raisrz.rais_project.main.MainActivity
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
 
   fun inject(activity: MainActivity)
   fun inject(activity: FavoriteActivity)
   fun inject(activity: DetailActivity)
}
