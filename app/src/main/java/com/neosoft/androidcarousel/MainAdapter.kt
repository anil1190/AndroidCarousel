package com.neosoft.androidcarousel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.neosoft.androidcarousel.databinding.AdapterMovieBinding
import com.neosoft.androidcarousel.models.Movie

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {

    var movies = mutableListOf<Movie>()
    fun setMovieList(movies: List<Movie>) {
        this.movies = movies.toMutableList()
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = DataBindingUtil.inflate<AdapterMovieBinding>(LayoutInflater.from(parent.context),R.layout.adapter_movie,parent,false)
        return MainViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(movies[position])

    }
    override fun getItemCount(): Int {
        return movies.size
    }

}


class MainViewHolder(private val itemBinding: AdapterMovieBinding) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(movie: Movie){
        itemBinding.movieList = movie
        itemBinding.executePendingBindings()
    }
}

