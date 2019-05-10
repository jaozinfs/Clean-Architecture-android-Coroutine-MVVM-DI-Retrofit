package com.prosegur.starwars.features.thmovie.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.prosegur.domain.th_movies.entities.THMovie

import com.prosegur.starwars.features.BaseActivity
import com.prosegur.starwars.features.thmovie.adapter.THMovieAdapter
import com.prosegur.starwars.utils.launchUI
import kotlinx.android.synthetic.main.th_movies_activity.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import androidx.core.view.MenuItemCompat.getActionView
import android.content.Context.SEARCH_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.app.SearchManager
import android.content.Context
import android.net.Uri
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.TransitionManager
import com.prosegur.data.films.BASE_BACKDROP_IMAGE_PATTER
import com.prosegur.s.R
import com.prosegur.starwars.utils.loadImageUrl
import kotlinx.android.synthetic.main.layout_listmodel_th_movie.*


class THListActivity : BaseActivity(), SearchView.OnQueryTextListener{
    override fun onQueryTextSubmit(query: String?): Boolean {

        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Timber.d("On Query Text Change: $newText")
        return true
    }

    private val viewModel: THListViewModel by viewModel()
    private lateinit var adapter: THMovieAdapter
    private var changed = false

    val constraintSet1 = ConstraintSet()
    val constraintSet2 = ConstraintSet()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.th_movies_activity)
        setupViews()
        setupObservers()
        loadData()



        constraintSet1.clone(constrantLayout)

        constraintSet2.clone(this, R.layout.th_movies_activity_expanded)





    }

    private fun setupViews() {
        adapter = THMovieAdapter(::startMovieDetails)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun startMovieDetails( position: Int, movie:  THMovie) {

        changeLayout(movie)
    }

    private fun loadData() {
        launchUI {
            viewModel.loadCharacters()
        }
    }

    private fun setupObservers() {
        viewModel.observe(this, ::showProgressDialog, ::stopProgressDialog, ::showToastMessage, ::handleData, null, null)

    }
    fun handleData(movies: List<THMovie>?){
       adapter.submitList(movies)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.list_th_movies_menu, menu)

        val mSearchView = menu?.findItem(R.id.action_search)
            ?.actionView as SearchView


        mSearchView.queryHint = getString(R.string.search_hint)


        mSearchView.setOnQueryTextListener(this)
        return true
    }

    fun changeLayout(movie: THMovie?){
        TransitionManager.beginDelayedTransition(constrantLayout)


        val constraint = if (changed){
            th_movies_list_activity_coverBg.visibility = View.GONE
            constraintSet1
        } else {
            Timber.d("load")
            val posterUri = Uri.parse(BASE_BACKDROP_IMAGE_PATTER)
                .buildUpon()
                .appendEncodedPath(movie?.backdrop_path)
                .build()
            th_movies_list_activity_coverBg.visibility = View.VISIBLE
            th_movies_list_activity_coverBg.loadImageUrl(posterUri)
            constraintSet2
        }
        constraint.applyTo(constrantLayout)
        changed = !changed
    }


    fun btBackLayout(view: View){
        changeLayout(null)
    }
}