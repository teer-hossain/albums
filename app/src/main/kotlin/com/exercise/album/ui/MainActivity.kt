package com.exercise.album.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.exercise.album.R
import com.exercise.album.ext.addFragment
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setTitle(R.string.app_name)

        if (savedInstanceState == null) addFragment(R.id.container, AlbumListFragment.newInstance())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if ((item?.itemId == R.id.action_settings)) {
            toast("Open settings")
            return true
        }
        return super.onOptionsItemSelected(item)

    }
}
