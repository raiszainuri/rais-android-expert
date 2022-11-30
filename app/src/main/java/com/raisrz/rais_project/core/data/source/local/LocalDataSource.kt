package com.raisrz.rais_project.core.data.source.local

import com.raisrz.rais_project.core.data.source.local.entity.SportEntity
import com.raisrz.rais_project.core.data.source.local.room.SportDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val sportDao: SportDao) {

    /*companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(sportDao: SportDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(sportDao)
            }
    }*/

    fun getAllSports(): Flow<List<SportEntity>> = sportDao.getAllSports()

    fun getFavoriteSport(): Flow<List<SportEntity>> = sportDao.getFavoriteSports()

    fun getFavoriteSportById(sportId: String): Flow<List<SportEntity>> =
        sportDao.getFavoriteSportsById(sportId)

    suspend fun insertSport(sportList: List<SportEntity>) = sportDao.insertSport(sportList)

    fun setFavoriteSport(sport: SportEntity, newState: Boolean) {
        sport.isFavorite = newState
        sportDao.updateFavoriteSport(sport)
    }
}