package com.prosegur.starwars.features.thmovie.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.prosegur.domain.th_movies.entities.THMovie

import com.prosegur.starwars.features.BaseActivity
import com.prosegur.starwars.features.thmovie.adapter.THMovieAdapter
import kotlinx.android.synthetic.main.th_movies_activity.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.net.Uri
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.TransitionManager
import com.prosegur.data.films.BASE_BACKDROP_IMAGE_PATTER
import com.prosegur.s.R
import com.prosegur.starwars.utils.loadImageUrl

class THListActivity : BaseActivity(), SearchView.OnQueryTextListener{
    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    private val viewModel: THListViewModel by viewModel()
    private lateinit var adapter: THMovieAdapter


    val constraintSet1 = ConstraintSet()
    val constraintSet2 = ConstraintSet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.th_movies_activity)
        setupViews()
        setupObservers()

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


    private fun setupObservers() {
        viewModel.getMutableSetDetailsState().observe(this, androidx.lifecycle.Observer {
            when(it){
                true->setDetails()
                false->removeDetails()
            }
        })
        viewModel.getDetailsDescription().observe(this, androidx.lifecycle.Observer {
            th_movies_list_activity_description.text = it
        })
        viewModel.getDetailsTitle().observe(this, androidx.lifecycle.Observer {
            th_movies_list_activity_title.text = it
        })

        viewModel.getDetailsRating().observe(this, androidx.lifecycle.Observer {
            th_movies_list_activity_rating_textView.text = it
        })
        viewModel.getDetailsDate().observe(this, androidx.lifecycle.Observer {
            th_movies_list_activity_date_textView.text = it
        })
        viewModel.getDetailsImage().observe(this, androidx.lifecycle.Observer {
            val posterUri = Uri.parse(BASE_BACKDROP_IMAGE_PATTER)
                .buildUpon()
                .appendEncodedPath(it)
                .build()

            th_movies_list_activity_coverBg.loadImageUrl(posterUri)
        })

        viewModel.getPosts().observe(this, androidx.lifecycle.Observer {
            adapter.submitList(it)
        })


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

    private fun changeLayout(movie: THMovie?){
        viewModel.changeDetailsLayout(movie)
    }

    private fun setDetails(){
        TransitionManager.beginDelayedTransition(constrantLayout)
        constraintSet2.applyTo(constrantLayout)
    }

    private fun removeDetails(){
        TransitionManager.beginDelayedTransition(constrantLayout)
        constraintSet1.applyTo(constrantLayout)
    }

    fun btBackLayout(view: View){
        changeLayout(null)
    }
}