package com.raisrz.rais_project.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.raisrz.rais_project.core.domain.usecase.SportUsecase

class MainViewModel(sportUsecase: SportUsecase) : ViewModel() {
    val sports = sportUsecase.getAllSports().asLiveData()
}
