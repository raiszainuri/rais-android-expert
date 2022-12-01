package com.raisrz.rais_project.core.data

import com.raisrz.rais_project.core.data.source.local.LocalDataSource
import com.raisrz.rais_project.core.data.source.remote.RemoteDataSource
import com.raisrz.rais_project.core.data.source.remote.network.ApiResponse
import com.raisrz.rais_project.core.data.source.remote.responses.SportsItem
import com.raisrz.rais_project.core.domain.model.Sport
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
    override fun getAllSports(): Flow<Resource<List<Sport>>> =
        object : NetworkBoundResource<List<Sport>, List<SportsItem>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Sport>> {
                return localDataSource.getAllSports().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Sport>?): Boolean = true
            //data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<SportsItem>>> =
                remoteDataSource.getAllSports()

            override suspend fun saveCallResult(data: List<SportsItem>) {
                val sportList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertSport(sportList)
            }
        }.asFlow()

    override fun getFavoriteSport(): Flow<List<Sport>> {
        return localDataSource.getFavoriteSport().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun getFavoriteSportById(sportId: String): Flow<List<Sport>> {
        return localDataSource.getFavoriteSportById(sportId)
            .map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteSport(sport: Sport, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteSport(
                DataMapper.mapDomainToEntity(sport),
                state
            )
        }
    }
}

