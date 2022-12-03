package com.raisrz.rais_project.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.raisrz.rais_project.core.domain.usecase.SportUsecase
import javax.inject.Inject

class MainViewModel @Inject constructor(sportUsecase: SportUsecase) : ViewModel() {
    val sports = sportUsecase.getAllSports().asLiveData()
}
