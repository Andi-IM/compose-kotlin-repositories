package com.matias.kotlinrepositories.ui.screens.search

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.performTextInput
import com.matias.kotlinrepositories.R
import com.matias.kotlinrepositories.util.BaseMockWebserverTest
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

@HiltAndroidTest
class SearchScreenIntegrationTest : BaseMockWebserverTest() {

    @Test
    fun givenNoSearchTerm_Then_EmptyStateIsShown() {
        setScreen()

        composeTestRule
            .onNode(hasText(composeTestRule.activity.getString(R.string.start_searching)), true)
            .assertIsDisplayed()
    }

    @Test
    fun givenNoSearchTerm_WhenInputChangesToTest_Then_CorrectDataIsLoadedFromBackend() {
        setScreen()

        composeTestRule.onNode(hasText(getString(R.string.search))).performTextInput("test")
        val lazyColumn = composeTestRule.onNode(hasScrollAction())

        lazyColumn.onChildAt(0).assert(hasText("kotest/kotest")).assertIsDisplayed()
        lazyColumn
            .onChildAt(1)
            .assert(hasText("googlecodelabs/android-testing"))
            .assertIsDisplayed()
        lazyColumn.onChildAt(2).assert(hasText("KasperskyLab/Kaspresso")).assertIsDisplayed()
    }

    private fun setScreen() {
        composeTestRule.setContent { SearchScreen() }
    }
}
