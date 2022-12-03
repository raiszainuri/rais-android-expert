package com.raisrz.rais_project.core.domain.repository

import com.raisrz.rais_project.core.data.Resource
import com.raisrz.rais_project.core.domain.model.Sport
import kotlinx.coroutines.flow.Flow

interface ISportRepository {
 
   fun getAllSports(): Flow<Resource<List<Sport>>>
 
   fun getFavoriteSport(): Flow<List<Sport>>
   fun getFavoriteSportById(sportId: String): Flow<List<Sport>>
 
   fun setFavoriteSport(sport: Sport, state: Boolean)
 
}