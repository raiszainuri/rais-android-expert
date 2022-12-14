package com.raisrz.rais_project.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.raisrz.rais_project.core.domain.model.Sport
import com.raisrz.rais_project.core.domain.usecase.SportUsecase
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val sportUsecase: SportUsecase) : ViewModel() {
    fun setFavSport(sport: Sport, newStatus: Boolean) =
        sportUsecase.setFavoriteSport(sport, newStatus)

    fun getFavSport(sportId: String) = sportUsecase.getFavoriteSportById(sportId = sportId).asLiveData()
}