package com.prosegur.starwars

import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.prosegur.s.R
import com.prosegur.starwars.features.starwars.view.list.SWListActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule




/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class SWActivityTest {


    @Rule
    @JvmField
    val rule = ActivityTestRule(SWListActivity::class.java)

    @Test
    fun whenActivityIsLaunched_shouldDisplayInitialState() {
        ///verify progress visibility
        onView(withId(R.id.progressDialog)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView)).check(matches(isNotChecked()))


    }
}
