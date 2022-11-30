package com.raisrz.rais_project.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.raisrz.rais_project.core.data.SportRepository

class MainViewModel(sportRepository: SportRepository) : ViewModel() {
    val sports = sportRepository.getAllSports().asLiveData()
}
