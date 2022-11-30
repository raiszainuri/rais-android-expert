package com.raisrz.rais_project.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.raisrz.rais_project.core.data.source.local.entity.SportEntity
import com.raisrz.rais_project.core.domain.usecase.SportUsecase

class DetailViewModel(private val sportUsecase: SportUsecase) : ViewModel() {
    fun setFavSport(sport: SportEntity, newStatus: Boolean) =
        sportUsecase.setFavoriteSport(sport, newStatus)

    fun getFavSport(sportId: String) = sportUsecase.getFavoriteSportById(sportId = sportId).asLiveData()
}