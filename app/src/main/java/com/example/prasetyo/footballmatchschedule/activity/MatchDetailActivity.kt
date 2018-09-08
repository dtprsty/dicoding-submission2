package com.example.prasetyo.footballmatchschedule.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.prasetyo.footballmatchschedule.R
import com.example.prasetyo.footballmatchschedule.api.TheSportDBApi
import com.example.prasetyo.footballmatchschedule.fragment.PreviousMatchFragment
import org.json.JSONException
import org.json.JSONObject

class MatchDetailActivity : AppCompatActivity() {

    private val TAG = PreviousMatchFragment::class.java.getSimpleName()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        val idHomeTeam = intent.getStringExtra("idHomeTeam")
        val idAwayTeam = intent.getStringExtra("idAwayTeam")
        val idEvent = intent.getStringExtra("idEvent")

        getMatchDetails(idEvent)
        getHomeTeamBadge(idHomeTeam)
        getAwayTeamBadge(idAwayTeam)

        showLoading()

    }

    fun getMatchDetails(id: String) {

        AndroidNetworking.get(TheSportDBApi.matchDetails(id))
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        // do anything with response
                        Log.e(TAG, "RESULT " + response.toString())
                        try {
                            val array = response.getJSONArray("events")
                            // Loop through the array elements
                            for (i in 0 until array.length()) {
                                // Get current json object
                                val jsonObject = array.getJSONObject(i)

                                txDate.text = jsonObject.getString("strDate")
                                txHomeTeam.text = jsonObject.getString("strHomeTeam")
                                txAwayTeam.text = jsonObject.getString("strAwayTeam")
                                txHomeScore.text = jsonObject.getString("intHomeScore")
                                txAwayScore.text = jsonObject.getString("intAwayScore")
                                txHomeDefense.text = jsonObject.getString("strHomeLineupDefense").replace(";", "\n")
                                txHomeGoalKeeper.text = jsonObject.getString("strHomeLineupGoalkeeper").replace(";", "\n")
                                txHomeMidfield.text = jsonObject.getString("strHomeLineupMidfield").replace(";", "\n")
                                txHomeForward.text = jsonObject.getString("strHomeLineupForward").replace(";", "\n")
                                txHomeGoals.text = jsonObject.getString("strHomeGoalDetails").replace(";", "\n")
                                txHomeSubtitutes.text = jsonObject.getString("strHomeLineupSubstitutes").replace(";", "\n")
                                txHomeYellowCard.text = jsonObject.getString("strHomeYellowCards").replace(";", "\n")
                                txHomeRedCard.text = jsonObject.getString("strHomeRedCards").replace(";", "\n")
                                txAwayDefense.text = jsonObject.getString("strAwayLineupDefense").replace(";", "\n")
                                txAwayGoalKeeper.text = jsonObject.getString("strAwayLineupGoalkeeper").replace(";", "\n")
                                txAwayMidfield.text = jsonObject.getString("strAwayLineupMidfield").replace(";", "\n")
                                txAwayForward.text = jsonObject.getString("strAwayLineupForward").replace(";", "\n")
                                txAwayGoals.text = jsonObject.getString("strAwayGoalDetails").replace(";", "\n")
                                txAwaySubtitutes.text = jsonObject.getString("strAwayLineupSubstitutes").replace(";", "\n")
                                txAwayYellowCards.text = jsonObject.getString("strAwayYellowCards").replace(";", "\n")
                                txAwayRedCards.text = jsonObject.getString("strAwayRedCards").replace(";", "\n")


                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }

                        hideLoading()

                    }

                    override fun onError(error: ANError) {
                        // handle error
                        hideLoading()
                    }
                })
    }

    fun getHomeTeamBadge(id: String) {

        AndroidNetworking.get(TheSportDBApi.teamDetaild(id))
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        // do anything with response
                        Log.e(TAG, "RESULT " + response.toString())
                        try {
                            val array = response.getJSONArray("teams")
                            // Loop through the array elements
                            for (i in 0 until array.length()) {
                                // Get current json object
                                val jsonObject = array.getJSONObject(i)

                                homeBadge.setImageUrl(jsonObject.getString("strTeamBadge"))


                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }


                    }

                    override fun onError(error: ANError) {
                        // handle error
                    }
                })
    }

    fun getAwayTeamBadge(id: String) {

        AndroidNetworking.get(TheSportDBApi.teamDetaild(id))
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        // do anything with response
                        Log.e(TAG, "RESULT " + response.toString())
                        try {
                            val array = response.getJSONArray("teams")
                            // Loop through the array elements
                            for (i in 0 until array.length()) {
                                // Get current json object
                                val jsonObject = array.getJSONObject(i)

                                awayBadge.setImageUrl(jsonObject.getString("strTeamBadge"))


                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }


                    }

                    override fun onError(error: ANError) {
                        // handle error
                    }
                })
    }

    fun hideLoading() {
        progressBar.invisible()
    }

    fun showLoading() {
        progressBar.visible()//To change body of created functions use File | Settings | File Templates.
    }
}
