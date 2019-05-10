package com.prosegur.starwars.features.starwars.view.details

import android.os.Bundle
import com.prosegur.domain.movies.entities.Movie
import com.prosegur.s.R
import com.prosegur.starwars.features.BaseActivity
import com.prosegur.starwars.utils.dateToString
import com.prosegur.starwars.utils.launchUI
import kotlinx.android.synthetic.main.sw_details_activity.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception

/**
 * Created by João Victor Oliveira on 07,Maio,2019
 */

class SWDetailsActivity: BaseActivity(){


    private val mViewModel: SWDetailsViewModel by viewModel()
    private var movieId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sw_details_activity)

        setupObservers()

        try{
            val b = intent.extras
            movieId = b.getInt("movieId")

            if (!mViewModel.triedLoadAtLeastOnce) {
                retrieveData()
            }
        }catch (error: Exception){
            //
        }
    }


    private fun retrieveData() {
        launchUI {
            mViewModel.loadMovie(movieId)
        }
    }

    private fun setupObservers() {
        mViewModel.observe(this, ::showProgressDialog, ::stopProgressDialog, ::showToastMessage, ::handleData, ::showShimmer, ::stopShimmer)


    }

    private fun handleData( movie: Movie? ){
        // set title activity
        title = movie?.title

        //description movie
        descriptionTextView.text = movie?.opening_crawl
        producerTextView.text = movie?.producer
        directorTextView.text = movie?.director
        dateTextView.text = movie?.release_date?.dateToString()

    }

    override fun shouldShowShimmer(): Boolean {
        return true
    }
}