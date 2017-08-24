package com.swensun.swdesign.ui

import android.app.ActivityOptions
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.swensun.swdesign.R
import com.swensun.swdesign.ui.animator.AnimatorActivity
import com.swensun.swdesign.ui.animator.PathMeasureActivity
import com.swensun.swdesign.ui.bottom.BottomNavigationActivity
import com.swensun.swdesign.ui.recycler.RecyclerViewActivity
import com.swensun.swdesign.ui.scroll.ScrollingIntroActivity
import com.swensun.swdesign.ui.viewpager.ViewPagerActivity
import com.swensun.swdesign.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.nav_header_main.view.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, LifecycleRegistryOwner {

    private val lifecycleRegistry = LifecycleRegistry(this)

    override fun getLifecycle(): LifecycleRegistry {
        return lifecycleRegistry
    }
    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        nav_view.getHeaderView(0).nav_header_layout.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
            drawer_layout.closeDrawer(GravityCompat.START)
        }

        setView()

    }

    private fun setView() {

//        val movies = resources.openRawResource(R.raw.doubanmovie).bufferedReader().use { it.readText() }
//        val doubanMovie = Gson().fromJson(movies, DoubanMovie::class.java)
//        Logger.d(doubanMovie.title)
        app_info_layout.setOnClickListener {
            startActivity(Intent(this, PathMeasureActivity::class.java))
        }

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when(item.itemId) {
            R.id.nav_recycler_view -> {
                startActivity(Intent(this, RecyclerViewActivity::class.java), ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
            }
            R.id.nav_scroll -> {
                startActivity(Intent(this, ScrollingIntroActivity::class.java), ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
            }
            R.id.nav_bottom_navigation -> {
                startActivity(Intent(this, BottomNavigationActivity::class.java), ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
            }
            R.id.nav_view_pager -> {
                startActivity(Intent(this, ViewPagerActivity::class.java), ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
            }
            R.id.nav_animator -> {
                startActivity(Intent(this, AnimatorActivity::class.java), ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
