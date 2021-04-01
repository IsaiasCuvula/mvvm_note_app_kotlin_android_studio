package com.bersyte.noteapp.fragments

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.bersyte.noteapp.MainActivity
import com.bersyte.noteapp.R
import com.bersyte.noteapp.adapter.NoteAdapter.NoteViewHolder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class HomeFragmentTest {

    @get: Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    val LIST_ITEM = 0
    val EXPECTED_NAME = "Isaias"


    @Test
    fun test_noNote_CardView() {

        onView(withId(R.id.cardView))
            .check(matches(isDisplayed()))

        onView(withId(R.id.fabAddNote))
            .check(matches(isDisplayed()))

    }

    @Test
    fun test_recyclerView_visibility() {

        onView(withId(R.id.recyclerView))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_selectedListItem() {

        //Create Note
        onView(
            withId(
                R.id.fabAddNote
            )
        )
            .perform(click())

        //enter some input
        onView(withId(R.id.etNoteTitle))
            .perform(ViewActions.typeText(EXPECTED_NAME))


        onView(withId(R.id.menu_save))
            .perform(click())

        onView(withId(R.id.recyclerView))
            .check(matches(isDisplayed()))


        //test onClick in RecyclerView
        onView(withId(R.id.recyclerView))
            .perform(
                actionOnItemAtPosition<NoteViewHolder>(
                    LIST_ITEM, click()

                )
            )

        onView(withId(R.id.etNoteTitleUpdate))
            .check(
                matches(
                    withText("Isaias")
                )
            )
    }

    @Test
    fun test_backNavigation_to_HomeFragment() {

        onView(withId(R.id.recyclerView))
            .check(matches(isDisplayed()))

        //test onClick in RecyclerView
        onView(withId(R.id.recyclerView))
            .perform(
                actionOnItemAtPosition<NoteViewHolder>(
                    LIST_ITEM, click()
                )
            )

        onView(withId(R.id.etNoteTitleUpdate))
            .check(
                matches(
                    withText("Isaias")
                )
            )

        pressBack()

        /* onView(withId(R.id.home_frag))
             .check(matches(isDisplayed()))

         onView(withId(R.id.recyclerView))
             .check(matches(isDisplayed()))
           */
    }
}