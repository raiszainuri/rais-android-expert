package com.raisrz.rais_project.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.raisrz.rais_project.core.data.SportRepository
import com.raisrz.rais_project.core.data.source.local.entity.SportEntity

class DetailViewModel(private val sportRepository: SportRepository) : ViewModel() {
    fun setFavSport(sport: SportEntity, newStatus: Boolean) =
        sportRepository.setFavoriteSport(sport, newStatus)

    fun getFavSport(sportId: String) = sportRepository.getFavoriteSportById(sportId = sportId).asLiveData()
}