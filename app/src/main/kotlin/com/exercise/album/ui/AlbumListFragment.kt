package com.exercise.album.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.exercise.album.R
import com.exercise.album.data.Album
import com.exercise.album.ext.*
import com.exercise.album.support.entities.Outcome
import kotlinx.android.synthetic.main.fragment_album_list.*
import kotlinx.android.synthetic.main.item_album.view.*
import org.jetbrains.anko.toast

class AlbumListFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    companion object {
        fun newInstance() = AlbumListFragment()
    }

    private lateinit var mViewModel: AlbumViewModel

    private val mAlbumObserver = Observer<Outcome<List<Album>>> {
        when (it) {
            is Outcome.Progress -> albumSwipeRefresh.isRefreshing = it.loading
            is Outcome.Failure -> updateEmptyList(it.e.getMessage())
            is Outcome.Success -> updateList(it.data)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.run {
            mViewModel = ViewModelProviders.of(this).get(AlbumViewModel::class.java)
        } ?: throw RuntimeException("Invalid activity")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel.albumsOutcome.reObserve(this, mAlbumObserver)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_album_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        albumSwipeRefresh.setOnRefreshListener(this)

        albumList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            layoutAnimation = AnimationUtils.loadLayoutAnimation(context, R.anim.anim_layout_fade_in)
            adapter = AlbumAdapter { onAlbumSelected(it) }
        }

        // Show message to user if Internet is unavailable
        context?.run { if (!hasConnection()) albumList.snack(R.string.err_no_internet) }
        // Fetch album data
        mViewModel.getAlbum()
    }

    override fun onRefresh() {
        mViewModel.getAlbum()
    }

    // Update recycler view with data
    private fun updateList(albums: List<Album>) {
        albumSwipeRefresh.show()
        emptyMessage.gone()
        getAdapter().addAlbums(albums)
    }

    // Show proper message to user if data unavailable
    private fun updateEmptyList(messageResId: Int) {
        albumSwipeRefresh.gone()
        emptyMessage.show()
        emptyMessage.setText(messageResId)
    }

    private fun getAdapter() = albumList.adapter as AlbumAdapter

    private fun onAlbumSelected(album: Album) {
        context?.toast(album.toString())
    }


}