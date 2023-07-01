package com.matias.kotlinrepositories.ui.navigation

import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.performClick
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.matias.kotlinrepositories.R
import com.matias.kotlinrepositories.util.BaseMockWebserverTest
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertEquals
import org.junit.Test

@HiltAndroidTest
class NavigationKtTest : BaseMockWebserverTest() {

    private lateinit var navController: NavHostController

    @Test
    fun given_NoAction_Then_CurrentDestinationIsHome() {
        setMainNavigation()

        assertEquals(Screen.Home.route, navController.currentDestination?.route)
    }

    @Test
    fun when_ClickedOnFirstElement_Then_CurrentDestinationIsDetails() {
        setMainNavigation()

        clickFirstItem()

        assertEquals(Screen.Details.route, navController.currentDestination?.route)
        assertEquals("square", navController.backQueue.last().arguments?.getString("owner"))
        assertEquals("okhttp", navController.backQueue.last().arguments?.getString("name"))
    }

    @Test
    fun givenClickedOnFirstItem_When_ClickedOnBack_Then_CurrentDestinationIsHome() {
        setMainNavigation()

        clickFirstItem()

        clickBack()

        assertEquals(Screen.Home.route, navController.currentDestination?.route)
    }

    @Test
    fun when_ClickedOnSearch_Then_CurrentDestinationIsSearch() {
        setMainNavigation()

        clickSearch()

        assertEquals(Screen.Search.route, navController.currentDestination?.route)
    }

    @Test
    fun givenClickedOnSearch_When_ClickedOnBack_Then_CurrentDestinationIsHome() {
        setMainNavigation()

        clickSearch()

        clickBack()

        assertEquals(Screen.Home.route, navController.currentDestination?.route)
    }

    private fun clickFirstItem() {
        composeTestRule.onNode(hasScrollAction()).onChildAt(0).performClick()
    }

    private fun clickSearch() {
        composeTestRule.onNode(hasContentDescription(composeTestRule.activity.getString(R.string.search)))
            .performClick()
    }

    private fun clickBack() {
        composeTestRule.onNode(hasContentDescription(composeTestRule.activity.getString(R.string.back)))
            .performClick()
    }

    private fun setMainNavigation() {
        composeTestRule.setContent {
            navController = rememberNavController()
            Navigation(navController)
        }
        composeTestRule.waitForIdle()
    }
}
