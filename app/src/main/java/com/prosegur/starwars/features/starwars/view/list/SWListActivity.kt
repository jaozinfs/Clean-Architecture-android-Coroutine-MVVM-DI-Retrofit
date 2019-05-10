package com.prosegur.starwars.features.starwars.view.list

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.recyclerview.widget.LinearLayoutManager
import com.prosegur.domain.movies.entities.Movie
import com.prosegur.s.R
import com.prosegur.starwars.features.BaseActivity
import com.prosegur.starwars.features.starwars.adapter.MoviesAdapter
import com.prosegur.starwars.features.starwars.view.details.SWDetailsActivity
import com.prosegur.starwars.utils.launchUI
import kotlinx.android.synthetic.main.sw_list_activity.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.R.menu
import android.view.MenuInflater
import android.view.MenuItem


/**
 * Created by João Victor Oliveira on 07,Maio,2019
 */

class SWListActivity: BaseActivity(){

    private val mViewModel: SWListViewModel by viewModel()
    private lateinit var mViewAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sw_list_activity)

        setupViews()
        setupObservers()
        if (!mViewModel.triedLoadAtLeastOnce) {
            retrieveData()
        }
    }

    private fun setupViews(){
        mViewAdapter = MoviesAdapter(::showMovieDetail)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mViewAdapter
    }

    private fun retrieveData() {
        launchUI {
            mViewModel.loadCharacters()
        }
    }

    private fun setupObservers() {
        mViewModel.observe(this, ::showProgressDialog, ::stopProgressDialog, ::showToastMessage, ::handleData, null, null)
    }

    private fun handleData(data: List<Movie>?){
        mViewAdapter.submitList(data!!)
    }

    private fun showMovieDetail(position: Int, movie: Movie){

        val intent = Intent(this, SWDetailsActivity::class.java)
        intent.putExtra("movieId", position)

        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_sw_list_activity, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
           R.id.menu_sw_list_activity_sync ->  retrieveData()
        }

        return super.onOptionsItemSelected(item)
    }

}