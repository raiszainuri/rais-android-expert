package com.raisrz.rais_project.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.raisrz.rais_project.core.data.SportRepository
import com.raisrz.rais_project.core.di.Injection
import com.raisrz.rais_project.detail.DetailViewModel
import com.raisrz.rais_project.favorite.FavoriteViewModel
import com.raisrz.rais_project.main.MainViewModel

class ViewModelFactory private constructor(private val sportRepository: SportRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance
                ?: synchronized(this) {
                    instance
                        ?: ViewModelFactory(
                            Injection.provideRepository(
                                context
                            )
                        )
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(sportRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(sportRepository) as T
            }
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(sportRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}