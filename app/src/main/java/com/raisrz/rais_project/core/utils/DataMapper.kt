package com.raisrz.rais_project.core.utils

import com.raisrz.rais_project.core.data.source.local.entity.SportEntity
import com.raisrz.rais_project.core.data.source.remote.responses.SportsItem

object DataMapper {
    fun mapResponsesToEntities(input: List<SportsItem>): List<SportEntity> {
        val sportList = ArrayList<SportEntity>()
        input.map {
            val sport = SportEntity(
                idSport = it.idSport,
                strSport = it.strSport,
                strFormat = it.strFormat,
                strSportThumb = it.strSportThumb,
                strSportIconGreen = it.strSportIconGreen,
                strSportDescription = it.strSportDescription,
                isFavorite = false
            )
            sportList.add(sport)
        }
        return sportList
    }

    fun mapEntitiesToDomain(input: List<SportEntity>): List<SportEntity> {
        val sportList = ArrayList<SportEntity>()
        input.map {
            val sport = SportEntity(
                idSport = it.idSport,
                strSport = it.strSport,
                strFormat = it.strFormat,
                strSportThumb = it.strSportThumb,
                strSportIconGreen = it.strSportIconGreen,
                strSportDescription = it.strSportDescription,
                isFavorite = false
            )
            sportList.add(sport)
        }
        return sportList
    }
}