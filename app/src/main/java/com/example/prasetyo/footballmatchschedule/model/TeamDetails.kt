package com.example.prasetyo.footballmatchschedule.model

import com.google.gson.annotations.SerializedName

data class Team(

        @SerializedName("strTeamBadge")
        var teamBadge: String? = null
)