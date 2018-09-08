package com.example.prasetyo.footballmatchschedule.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.example.prasetyo.footballmatchschedule.R
import com.example.prasetyo.footballmatchschedule.fragment.NextMatchFragment
import com.example.prasetyo.footballmatchschedule.fragment.PreviousMatchFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.design.bottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var mainUI: MainActivityUI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainUI = MainActivityUI()
        mainUI.setContentView(this)

        loadFragment(PreviousMatchFragment())

        mainUI.bottomNavigationView.setOnNavigationItemSelectedListener (this)

    }


    private fun loadFragment(fragment: Fragment?): Boolean {
        //switching fragment
        if (fragment != null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit()
            return true
        }
        return false
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null

        when (item.itemId) {
            R.id.nav_prev_match -> fragment = PreviousMatchFragment()

            R.id.nav_next_match -> fragment = NextMatchFragment()
        }

        return loadFragment(fragment)

    }


    class MainActivityUI : AnkoComponent<MainActivity> {

        lateinit var bottomNavigationView: BottomNavigationView

        override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {

            relativeLayout{
                lparams(width = matchParent, height = wrapContent)

                frameLayout{
                    id = R.id.container
                    padding = dip(16)
                    lparams(width = matchParent, height = matchParent)

                }


                bottomNavigationView = bottomNavigationView{
                    fitsSystemWindows = true
                    inflateMenu(R.menu.navigation)
                    backgroundResource = R.color.colorPrimary
                    itemTextColor = resources.getColorStateList(R.color.white)
                    itemIconTintList = resources.getColorStateList(R.color.white)

                }.lparams(width = matchParent, height = wrapContent){
                    alignParentBottom()
                }
            }

        }
    }
}
