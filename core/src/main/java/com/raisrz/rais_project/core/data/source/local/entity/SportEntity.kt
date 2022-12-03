package com.raisrz.rais_project.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "sports")
data class SportEntity(
    @PrimaryKey
    @ColumnInfo(name = "idSport")
    var idSport: String,

    @ColumnInfo(name = "strSport")
    var strSport: String,

    @ColumnInfo(name = "strFormat")
    var strFormat: String,

    @ColumnInfo(name = "strSportThumb")
    var strSportThumb: String,

    @ColumnInfo(name = "strSportIconGreen")
    var strSportIconGreen: String,

    @ColumnInfo(name = "strSportDescription")
    var strSportDescription: String,

    @ColumnInfo(name  = "isFavorite")
    var isFavorite: Boolean
) : Parcelable
