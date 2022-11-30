package com.raisrz.rais_project.core.domain.repository

import com.raisrz.rais_project.core.data.Resource
import com.raisrz.rais_project.core.data.source.local.entity.SportEntity
import kotlinx.coroutines.flow.Flow

interface ISportRepository {
 
   fun getAllSports(): Flow<Resource<List<SportEntity>>>
 
   fun getFavoriteSport(): Flow<List<SportEntity>>
   fun getFavoriteSportById(sportId: String): Flow<List<SportEntity>>
 
   fun setFavoriteSport(sportEntity: SportEntity, state: Boolean)
 
}