package com.raisrz.rais_project.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.raisrz.rais_project.core.domain.usecase.SportUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(sportUsecase: SportUsecase) : ViewModel() {
    val sports = sportUsecase.getAllSports().asLiveData()
}
