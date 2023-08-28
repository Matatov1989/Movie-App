package com.example.movieapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.model.Movie
import com.example.movieapp.util.Constants.POSTER_IMG_URL

class MovieListAdapter (
    private val movies: List<Movie>,
    private val onItemClick: (Movie) -> Unit) :
    RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieListAdapter.MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieListAdapter.MovieViewHolder, position: Int) {
        Glide
            .with(holder.itemView)
            .load("${POSTER_IMG_URL}${movies[position].poster_path}")
            .centerCrop()
            .placeholder(R.drawable.ic_android)
            .into(holder.imageViewPoster)

        holder.textViewTitle.text = movies[position].title
        holder.textViewAvg.text = movies[position].vote_average

        holder.itemView.setOnClickListener {
            onItemClick.invoke(movies[position])
        }
    }

    override fun getItemCount(): Int = movies.size

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewPoster: ImageView = itemView.findViewById(R.id.imageViewPoster)
        val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        val textViewAvg: TextView = itemView.findViewById(R.id.textViewAvg)
    }
}