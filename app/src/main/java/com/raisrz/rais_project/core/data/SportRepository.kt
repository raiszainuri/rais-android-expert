package com.raisrz.rais_project.core.data

import com.raisrz.rais_project.core.data.source.local.LocalDataSource
import com.raisrz.rais_project.core.data.source.local.entity.SportEntity
import com.raisrz.rais_project.core.data.source.remote.RemoteDataSource
import com.raisrz.rais_project.core.data.source.remote.network.ApiResponse
import com.raisrz.rais_project.core.data.source.remote.responses.SportsItem
import com.raisrz.rais_project.core.domain.repository.ISportRepository
import com.raisrz.rais_project.core.utils.AppExecutors
import com.raisrz.rais_project.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SportRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ISportRepository {

    /*companion object {
        @Volatile
        private var instance: SportRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): SportRepository =
            instance ?: synchronized(this) {
                instance ?: SportRepository(remoteData, localData, appExecutors)
            }
    }*/

    override fun getAllSports(): Flow<Resource<List<SportEntity>>> =
        object : NetworkBoundResource<List<SportEntity>, List<SportsItem>>(appExecutors) {
            override fun loadFromDB(): Flow<List<SportEntity>> {
                return localDataSource.getAllSports().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<SportEntity>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<SportsItem>>> =
                remoteDataSource.getAllSports()

            override suspend fun saveCallResult(data: List<SportsItem>) {
                val sportList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertSport(sportList)
            }
        }.asFlow()

    override fun getFavoriteSport(): Flow<List<SportEntity>> {
        return localDataSource.getFavoriteSport().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun getFavoriteSportById(sportId: String): Flow<List<SportEntity>> {
        return localDataSource.getFavoriteSportById(sportId)
    }

    override fun setFavoriteSport(sport: SportEntity, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setFavoriteSport(sport, state) }
    }
}

