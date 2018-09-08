package com.example.prasetyo.footballmatchschedule.model

import com.google.gson.annotations.SerializedName

data class MatchDetails(

        @SerializedName("strHomeGoalDetails")
        var strHomeGoalDetails: String? = null,

        @SerializedName("strHomeRedCard")
        var strHomeRedCard: String? = null,

        @SerializedName("strHomeYellowCards")
        var strHomeYellowCards: String? = null,

        @SerializedName("strHomeLineupGoalkeeper")
        var strHomeLineupGoalkeeper: String? = null,

        @SerializedName("strHomeLineupDefense")
        var strHomeLineupDefense: String? = null,

        @SerializedName("strHomeLineupMidfield")
        var strHomeLineupMidfield: String? = null,

        @SerializedName("strHomeLineupForward")
        var strHomeLineupForward: String? = null,

        @SerializedName("strHomeLineupSubstitutes")
        var strHomeLineupSubstitutes: String? = null,

        @SerializedName("strHomeFormation")
        var strHomeFormation: String? = null,

        @SerializedName("strAwayGoalDetails")
        var strAwayGoalDetails: String? = null,

        @SerializedName("strAwayRedCard")
        var strAwayRedCard: String? = null,

        @SerializedName("strAwayYellowCards")
        var strAwayYellowCards: String? = null,

        @SerializedName("strAwayLineupGoalkeeper")
        var strAwayLineupGoalkeeper: String? = null,

        @SerializedName("strAwayLineupDefense")
        var strAwayLineupDefense: String? = null,

        @SerializedName("strAwayLineupMidfield")
        var strAwayLineupMidfield: String? = null,

        @SerializedName("strAwayLineupForward")
        var strAwayLineupForward: String? = null,

        @SerializedName("strAwayLineupSubstitutes")
        var strAwayLineupSubstitutes: String? = null,

        @SerializedName("strAwayFormation")
        var strAwayFormation: String? = null
)