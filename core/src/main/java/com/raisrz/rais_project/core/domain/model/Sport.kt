package com.raisrz.rais_project.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sport(
    var idSport: String,
    var strSport: String,
    var strFormat: String,
    var strSportThumb: String,
    var strSportIconGreen: String,
    var strSportDescription: String,
    var isFavorite: Boolean
) : Parcelable
