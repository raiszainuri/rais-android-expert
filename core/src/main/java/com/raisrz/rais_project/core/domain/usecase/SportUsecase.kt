package com.raisrz.rais_project.core.domain.usecase

import com.raisrz.rais_project.core.data.Resource
import com.raisrz.rais_project.core.domain.model.Sport
import kotlinx.coroutines.flow.Flow

interface SportUsecase {
 
   fun getAllSports(): Flow<Resource<List<Sport>>>
 
   fun getFavoriteSport(): Flow<List<Sport>>
   fun getFavoriteSportById(sportId: String): Flow<List<Sport>>
 
   fun setFavoriteSport(sport: Sport, state: Boolean)
 
}