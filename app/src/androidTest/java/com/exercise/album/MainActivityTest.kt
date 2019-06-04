package com.exercise.album

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.exercise.album.ext.addFragment
import com.exercise.album.ui.AlbumListFragment
import com.exercise.album.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val mainActivityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun test_fragment_initiated() {
        val fragment = AlbumListFragment.newInstance()
        mainActivityTestRule.activity.addFragment(
            R.id.container,
            fragment
        )
    }

}