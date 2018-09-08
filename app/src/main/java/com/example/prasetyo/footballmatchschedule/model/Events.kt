package com.example.prasetyo.footballmatchschedule.model

import com.google.gson.annotations.SerializedName

data class Events(

        @SerializedName("strLeague")
        var leagueName: String? = null,

        @SerializedName("strHomeTeam")
        var homeTeam: String? = null,

        @SerializedName("strAwayTeam")
        var awayTeam: String? = null,

        @SerializedName("intRound")
        var intRound: Int? = null,

        @SerializedName("strDate")
        var strDate: String? = null,

        @SerializedName("strTime")
        var strTime: String? = null,

        @SerializedName("idHomeTeam")
        var idHomeTeam: String? = null,

        @SerializedName("idAwayTeam")
        var idAwayTeam: String? = null,

        @SerializedName("intHomeScore")
        var homeScore: Int? = null,

        @SerializedName("intAwayScore")
        var awayScore: Int? = null,

        @SerializedName("strFileName")
        var fileName: String? = null,


        @SerializedName("idEvent")
        var idEvent: String? = null
)