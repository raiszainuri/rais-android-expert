package com.raisrz.rais_project.core.data.source.remote.responses

import com.google.gson.annotations.SerializedName

data class SportResponse(

	@field:SerializedName("sports")
	val sports: List<SportsItem>
)