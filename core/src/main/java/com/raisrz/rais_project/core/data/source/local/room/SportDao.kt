package com.raisrz.rais_project.core.data.source.local.room

import androidx.room.*
import com.raisrz.rais_project.core.data.source.local.entity.SportEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SportDao {

    @Query("SELECT * FROM sports")
    fun getAllSports(): Flow<List<SportEntity>>

    @Query("SELECT * FROM sports where isFavorite = 1")
    fun getFavoriteSports(): Flow<List<SportEntity>>

    @Query("SELECT * FROM sports where idSport = :sportId and isFavorite = 1")
    fun getFavoriteSportsById(sportId: String): Flow<List<SportEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSport(sport: List<SportEntity>)

    @Update
    fun updateFavoriteSport(sport: SportEntity)
}
