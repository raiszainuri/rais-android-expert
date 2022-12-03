package com.raisrz.rais_project.core.domain.usecase

import com.raisrz.rais_project.core.domain.model.Sport
import com.raisrz.rais_project.core.domain.repository.ISportRepository
import javax.inject.Inject

class SportInteractor @Inject constructor(private val sportRepository: ISportRepository):
    SportUsecase {

    override fun getAllSports() = sportRepository.getAllSports()

    override fun getFavoriteSport() = sportRepository.getFavoriteSport()
    override fun getFavoriteSportById(sportId: String) = sportRepository.getFavoriteSportById(sportId)

    override fun setFavoriteSport(sport: Sport, state: Boolean) = sportRepository.setFavoriteSport(sport, state)
}