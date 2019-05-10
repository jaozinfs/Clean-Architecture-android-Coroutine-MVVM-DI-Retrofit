package com.prosegur.starwars.features.thmovie.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.prosegur.data.films.BASE_BACKDROP_IMAGE_PATTER
import com.prosegur.data.films.BASE_URL
import com.prosegur.domain.movies.entities.Movie
import com.prosegur.domain.th_movies.entities.THMovie
import com.prosegur.s.R
import com.prosegur.starwars.features.starwars.adapter.MoviesAdapter
import com.prosegur.starwars.utils.dateToString
import com.prosegur.starwars.utils.loadImageUrl

open class THMovieAdapter (private val clickListener : (Int, THMovie)-> Unit) :  PagedListAdapter<THMovie, THMovieAdapter.THMoviewHolder>(DIFF_CALLBACK){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): THMoviewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_listmodel_th_movie, parent, false)
        return THMovieAdapter.THMoviewHolder(view)
    }

    override fun onBindViewHolder(holder: THMoviewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it)
            with(holder.itemView){
                setOnClickListener{clickListener(position, getItem(position)!!)}
            }
        }


    }


    class THMoviewHolder(itemview: View) : RecyclerView.ViewHolder(itemview){

        val image: ImageView = itemView.findViewById(R.id.listmodel_th_movie_cover_imageView)
        val title: TextView = itemView.findViewById(R.id.listmodel_th_movie_title_textView)
        val description: TextView = itemView.findViewById(R.id.listmodel_th_movie_description_textView)
        val voterAverage: TextView = itemView.findViewById(R.id.listmodel_th_movie_vote_a_textView)



        fun bind(movie: THMovie?){
            title.text = movie?.original_title
            description.text = movie?.overview
            voterAverage.text = movie?.vote_average.toString()


            movie?.backdrop_path?.let {

                val posterUri = Uri.parse(BASE_BACKDROP_IMAGE_PATTER)
                    .buildUpon()
                    .appendEncodedPath(movie.backdrop_path)
                    .build()

                image.loadImageUrl(posterUri)

            }

        }
    }


    object DIFF_CALLBACK : DiffUtil.ItemCallback<THMovie>() {
        override fun areItemsTheSame(oldItem: THMovie, newItem: THMovie): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: THMovie, newItem: THMovie): Boolean = oldItem == newItem
    }

}