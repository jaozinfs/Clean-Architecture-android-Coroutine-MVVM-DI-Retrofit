package com.prosegur.starwars.features.starwars.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.prosegur.domain.movies.entities.Movie
import com.prosegur.s.R
import com.prosegur.starwars.utils.dateToString


open class MoviesAdapter(private val clickListener: (Int, Movie) -> Unit) : ListAdapter<Movie, MoviesAdapter.MoviesViewHolder>(DIFF_CALLBACK) {

    private var movieList = mutableListOf<Movie>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_listmodel_movie, parent, false)
        return MoviesViewHolder(view)
    }


    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)

        with(holder.itemView){
            setOnClickListener{clickListener(position, movie)}
        }
    }



    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.findViewById(R.id.listmodel_title_movie)
        val date: TextView = itemView.findViewById(R.id.listmodel_date_movie)
        val description: TextView = itemView.findViewById(R.id.listmodel_description_movie)


        fun bind(movie: Movie?){
            title.text = movie?.title
            date.text = movie?.created?.dateToString()
            description.text = movie?.opening_crawl

        }
    }

    object DIFF_CALLBACK : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem.episode_id == newItem.episode_id

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem == newItem
    }
}