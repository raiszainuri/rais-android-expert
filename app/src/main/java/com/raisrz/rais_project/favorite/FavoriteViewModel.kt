package com.raisrz.rais_project.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.raisrz.rais_project.core.domain.model.Sport
import com.raisrz.rais_project.core.domain.usecase.SportUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val sportUsecase: SportUsecase) : ViewModel() {
    val favSport = sportUsecase.getFavoriteSport().asLiveData()
    fun removeFavSport(sport: Sport) =
        sportUsecase.setFavoriteSport(sport, false)
}