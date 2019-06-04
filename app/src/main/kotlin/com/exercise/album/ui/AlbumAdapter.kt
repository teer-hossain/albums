package com.exercise.album.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exercise.album.R
import com.exercise.album.data.Album
import kotlinx.android.synthetic.main.item_album.view.*

class AlbumAdapter(
    private val albums: ArrayList<Album> = arrayListOf(),
    private val itemClick: (Album) -> Unit
) : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    fun addAlbums(newAlbums: List<Album>) {
        albums.clear()
        albums.addAll(newAlbums)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bindAlbum(albums[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false), itemClick)

    override fun getItemCount(): Int = albums.size

    inner class ViewHolder(v: View, private val itemClick: (Album) -> Unit) : RecyclerView.ViewHolder(v) {

        var isInMiddle: Boolean = false

        fun bindAlbum(album: Album) = with(album) {
            isInMiddle = (albums.size / 2) == adapterPosition
            itemView.albumName.text = title
            itemView.setOnClickListener { itemClick(this) }
        }
    }

}
