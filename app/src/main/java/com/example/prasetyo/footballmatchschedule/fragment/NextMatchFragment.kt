package com.example.prasetyo.footballmatchschedule.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.prasetyo.footballmatchschedule.R
import com.example.prasetyo.footballmatchschedule.adapter.NextEventAdapter
import com.example.prasetyo.footballmatchschedule.api.TheSportDBApi
import com.example.prasetyo.footballmatchschedule.model.Events
import com.example.prasetyo.footballmatchschedule.util.invisible
import com.example.prasetyo.footballmatchschedule.util.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import org.json.JSONException
import org.json.JSONObject
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class NextMatchFragment : Fragment() {

    private val TAG = NextMatchFragment::class.java.getSimpleName()

    private val itemList = ArrayList<Events>()
    private lateinit var adapter: NextEventAdapter
    private var layoutManager: LinearLayoutManager? = null

    private lateinit var mainUI: NextMatchFragmentUI<NextMatchFragment>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        mainUI = NextMatchFragmentUI()
        return mainUI.createView(AnkoContext.create(ctx, this)).apply {
            itemList.clear()
            showLoading()
            getTeams()
            initRecyclerView()class NextMatchFragment : Fragment() {

    private val TAG = NextMatchFragment::class.java.getSimpleName()

    private val itemList = ArrayList<Events>()
    private lateinit var adapter: NextEventAdapter
    private var layoutManager: LinearLayoutManager? = null

    private lateinit var mainUI: NextMatchFragmentUI<NextMatchFragment>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        mainUI = NextMatchFragmentUI()
        return mainUI.createView(AnkoContext.create(ctx, this)).apply {
            itemList.clear()
            showLoading()
            getTeams()
            initRecyclerView()

            mainUI.swipeRefresh.onRefresh {
                mainUI.swipeRefresh.isRefreshing = false
                itemList.clear()
                adapter.notifyDataSetChanged()
                showLoading()
                getTeams()
                initRecyclerView()
            }

        }



    }

    companion object {
        fun newInstance(): NextMatchFragment {
            var fragmentHome = NextMatchFragment()
            var args = Bundle()
            fragmentHome.arguments = args
            return fragmentHome
        }

    }


    fun hideLoading() {
        mainUI.progressBar.invisible()
    }

    fun showLoading() {
        mainUI.progressBar.visible()//To change body of created functions use File | Settings | File Templates.
    }

    private fun initRecyclerView() {
        layoutManager = LinearLayoutManager(context)
        mainUI.listTeam.setLayoutManager(layoutManager)
        mainUI.listTeam.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        // untuk mengisi data dari JSON ke dalam adapter
        itemList.clear()
        adapter = NextEventAdapter(itemList)
        mainUI.listTeam.adapter = adapter
        adapter.notifyDataSetChanged()
        mainUI.swipeRefresh.setRefreshing(false)
    }

    fun getTeams() {

        AndroidNetworking.get(TheSportDBApi.nextEvents())
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

                                val events = Events()

                                events.strDate = jsonObject.getString("strDate")
                                events.homeTeam = jsonObject.getString("strHomeTeam")
                                events.awayTeam = jsonObject.getString("strAwayTeam")
                                events.idHomeTeam = jsonObject.getString("idHomeTeam")
                                events.idAwayTeam = jsonObject.getString("idAwayTeam")
                                events.idEvent = jsonObject.getString("idEvent")

                                itemList.add(events)

                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }

                        adapter.notifyDataSetChanged()
                        mainUI.swipeRefresh.isRefreshing = false
                        hideLoading()

                    }

                    override fun onError(error: ANError) {
                        // handle error
                        hideLoading()
                    }
                })


    }

    class NextMatchFragmentUI <T>: AnkoComponent<NextMatchFragment> {

        lateinit var listTeam: RecyclerView
        lateinit var progressBar: ProgressBar
        lateinit var swipeRefresh: SwipeRefreshLayout

        override fun createView(ui: AnkoContext<NextMatchFragment>) = with(ui) {

            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                orientation = LinearLayout.VERTICAL

                swipeRefresh = swipeRefreshLayout {
                    setColorSchemeResources(R.color.colorAccent,
                            android.R.color.holo_green_light,
                            android.R.color.holo_orange_light,
                            android.R.color.holo_red_light)

                    relativeLayout {
                        lparams(width = matchParent, height = wrapContent)

                        listTeam = recyclerView {
                            lparams(width = matchParent, height = wrapContent)
                            layoutManager = LinearLayoutManager(ctx)
                        }

                        progressBar = progressBar { }.lparams {
                            centerHorizontally()
                        }
                    }
                }

            }
        }

    }













        }


            mainUI.swipeRefresh.onRefresh {
                mainUI.swipeRefresh.isRefreshing = false
                itemList.clear()
                adapter.notifyDataSetChanged()
                showLoading()
                getTeams()
                initRecyclerView()
            }

        }



    }

    companion object {
        fun newInstance(): NextMatchFragment {
            var fragmentHome = NextMatchFragment()
            var args = Bundle()
            fragmentHome.arguments = args
            return fragmentHome
        }

    }


    fun hideLoading() {
        mainUI.progressBar.invisible()
    }

    fun showLoading() {
        mainUI.progressBar.visible()//To change body of created functions use File | Settings | File Templates.
    }

    private fun initRecyclerView() {
        layoutManager = LinearLayoutManager(context)
        mainUI.listTeam.setLayoutManager(layoutManager)
        mainUI.listTeam.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        // untuk mengisi data dari JSON ke dalam adapter
        itemList.clear()
        adapter = NextEventAdapter(itemList)
        mainUI.listTeam.setAdapter(adapter)
        adapter.notifyDataSetChanged()
        mainUI.swipeRefresh.setRefreshing(false)
    }

    fun getTeams() {

        AndroidNetworking.get(TheSportDBApi.nextEvents())
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

                                val events = Events()

                                events.strDate = jsonObject.getString("strDate")
                                events.homeTeam = jsonObject.getString("strHomeTeam")
                                events.awayTeam = jsonObject.getString("strAwayTeam")
                                events.idHomeTeam = jsonObject.getString("idHomeTeam")
                                events.idAwayTeam = jsonObject.getString("idAwayTeam")
                                events.idEvent = jsonObject.getString("idEvent")

                                itemList.add(events)

                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }

                        adapter.notifyDataSetChanged()
                        mainUI.swipeRefresh.isRefreshing = false
                        hideLoading()

                    }

                    override fun onError(error: ANError) {
                        // handle error
                        hideLoading()
                    }
                })


    }

    class NextMatchFragmentUI <T>: AnkoComponent<NextMatchFragment> {

        lateinit var listTeam: RecyclerView
        lateinit var progressBar: ProgressBar
        lateinit var swipeRefresh: SwipeRefreshLayout

        override fun createView(ui: AnkoContext<NextMatchFragment>) = with(ui) {

            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                orientation = LinearLayout.VERTICAL

                swipeRefresh = swipeRefreshLayout {
                    setColorSchemeResources(R.color.colorAccent,
                            android.R.color.holo_green_light,
                            android.R.color.holo_orange_light,
                            android.R.color.holo_red_light)

                    relativeLayout {
                        lparams(width = matchParent, height = wrapContent)

                        listTeam = recyclerView {
                            lparams(width = matchParent, height = wrapContent)
                            layoutManager = LinearLayoutManager(ctx)
                        }

                        progressBar = progressBar { }.lparams {
                            centerHorizontally()
                        }
                    }
                }

            }
        }

    }


}
