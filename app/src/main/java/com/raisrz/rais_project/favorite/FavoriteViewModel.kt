package com.raisrz.rais_project.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.raisrz.rais_project.core.data.source.local.entity.SportEntity
import com.raisrz.rais_project.core.domain.usecase.SportUsecase

class FavoriteViewModel(private val sportUsecase: SportUsecase) : ViewModel() {
    val favSport = sportUsecase.getFavoriteSport().asLiveData()
    fun removeFavSport(sport: SportEntity) =
        sportUsecase.setFavoriteSport(sport, false)
}