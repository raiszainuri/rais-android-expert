package com.raisrz.rais_project.core.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.raisrz.rais_project.core.domain.usecase.SportUsecase
import com.raisrz.rais_project.detail.DetailViewModel
import com.raisrz.rais_project.di.AppScope
import com.raisrz.rais_project.favorite.FavoriteViewModel
import com.raisrz.rais_project.main.MainViewModel
import javax.inject.Inject

@AppScope
class ViewModelFactory @Inject constructor(private val sportUsecase: SportUsecase) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(sportUsecase) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(sportUsecase) as T
            }
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(sportUsecase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}