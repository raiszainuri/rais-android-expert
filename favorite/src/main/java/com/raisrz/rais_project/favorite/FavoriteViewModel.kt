package com.raisrz.rais_project.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.raisrz.rais_project.core.domain.model.Sport
import com.raisrz.rais_project.core.domain.usecase.SportUsecase
import com.raisrz.rais_project.favorite.di.FavoriteScope
import javax.inject.Inject

@FavoriteScope
class FavoriteViewModel @Inject constructor( val sportUsecase: SportUsecase) : ViewModel() {
    val favSport = sportUsecase.getFavoriteSport().asLiveData()
    fun removeFavSport(sport: Sport) = sportUsecase.setFavoriteSport(sport, false)
}