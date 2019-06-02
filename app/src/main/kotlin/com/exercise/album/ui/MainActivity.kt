package com.exercise.album.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.exercise.album.R
import com.exercise.album.ext.addFragment
import org.jetbrains.anko.find

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(find(R.id.toolbar))
        supportActionBar?.setTitle(R.string.app_name)

        if (savedInstanceState == null) addFragment(R.id.container, AlbumListFragment.newInstance())
    }
}
