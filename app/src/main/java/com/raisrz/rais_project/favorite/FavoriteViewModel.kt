package com.raisrz.rais_project.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.raisrz.rais_project.core.data.SportRepository
import com.raisrz.rais_project.core.data.source.local.entity.SportEntity

class FavoriteViewModel(private val sportRepository: SportRepository) : ViewModel() {
    val favSport = sportRepository.getFavoriteSport().asLiveData()
    fun removeFavSport(sport: SportEntity) =
        sportRepository.setFavoriteSport(sport, false)
}