package com.raisrz.rais_project.core.utils

import android.content.Context
import com.raisrz.rais_project.R
import com.raisrz.rais_project.core.data.source.remote.responses.SportsItem
import org.json.JSONObject
import java.io.IOException
import java.util.*

class JsonHelper(private val context: Context) {

    private fun parsingFileToString(): String? {
        val jsonString: String
        try {
            jsonString = context.resources.openRawResource(R.raw.sports).bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    fun loadData(): List<SportsItem > {
        val list = ArrayList<SportsItem>()
        val responseObject = JSONObject(parsingFileToString().toString())
        val listArray = responseObject.getJSONArray("sports")
        for (i in 0 until listArray.length()) {
            val course = listArray.getJSONObject(i)

            val idSport = course.getString("idSport")
            val strSport = course.getString("strSport")
            val strFormat = course.getString("strFormat")
            val strSportThumb = course.getString("strSportThumb")
            val strSportIconGreen = course.getString("strSportIconGreen")
            val strSportDescription = course.getString("strSportDescription")

            val courseResponse = SportsItem(
                idSport = idSport,
                strSport = strSport,
                strFormat = strFormat,
                strSportThumb = strSportThumb,
                strSportIconGreen = strSportIconGreen,
                strSportDescription = strSportDescription
            )
            list.add(courseResponse)
        }
        return list
    }
}

