package com.raisrz.rais_project.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.raisrz.rais_project.core.di.AppScope
import com.raisrz.rais_project.core.domain.usecase.SportUsecase
import com.raisrz.rais_project.ui.detail.DetailViewModel
import com.raisrz.rais_project.ui.main.MainViewModel
import javax.inject.Inject

@AppScope
class ViewModelFactory @Inject constructor(private val sportUsecase: SportUsecase) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(sportUsecase) as T
        }
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(sportUsecase) as T
        }

        throw ClassNotFoundException()
    }
}