package com.example.prasetyo.footballmatchschedule.api

import com.example.prasetyo.footballmatchschedule.BuildConfig

object TheSportDBApi {
    fun nextEvents(): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventsnextleague.php?id=4328"
    }

    fun lastEvents(): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventspastleague.php?id=4328"
    }

    fun teamDetaild(id: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupteam.php?id=" + id
    }

    fun matchDetails(id: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupevent.php?id=" + id
    }
}