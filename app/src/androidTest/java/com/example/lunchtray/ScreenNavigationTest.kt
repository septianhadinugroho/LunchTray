package com.example.lunchtray

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import assertCurrentScreenName
import com.example.lunchtray.data.DataSource
import com.example.lunchtray.data.ScreenName
import com.example.lunchtray.ui.theme.LunchTrayTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class ScreenNavigationTest{
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupLunchTrayNavHost(){
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            LunchTrayTheme {
                App(viewModel = OrderViewModel(), navController = navController)
            }
        }
    }

    @Test
    fun lunchTray_verifyStartDestination(){
        navController.assertCurrentScreenName(ScreenName.Start.name)
    }

    @Test
    fun lunchTray_verifyBackNavigationNotShownOnStartScreen(){
        val backText = composeTestRule.activity.getString(R.string.back)
        composeTestRule.onNodeWithContentDescription(backText).assertDoesNotExist()
    }

    @Test
    fun lunchTray_clickStart_navigateToEntree(){
        navigateToEntree()
        navController.assertCurrentScreenName(ScreenName.EntreeMenu.name)
    }

    private fun navigateToEntree(){
        val startText = composeTestRule.activity.getString(R.string.start_order)
        composeTestRule.onNodeWithText(startText).performClick()
    }

    @Test
    fun lunchTray_selectEntreeNGoNext_navigateToSideDish(){
        navigateToSideDish()
        navController.assertCurrentScreenName(ScreenName.SideDishMenu.name)
    }

    private fun navigateToSideDish(){
        navigateToEntree()
        composeTestRule.onNodeWithText(DataSource.entree.first().title).performClick()
        val nextText = composeTestRule.activity.getString(R.string.next)
        composeTestRule.onNodeWithText(nextText).performClick()
    }

    @Test
    fun lunchTray_selectSideDishNGoNext_navigateToAccompaniment(){
        navigateToAccompaniment()
        navController.assertCurrentScreenName(ScreenName.AccompanimentMenu.name)
    }

    private fun navigateToAccompaniment(){
        navigateToSideDish()
        val nextText = composeTestRule.activity.getString(R.string.next)
        composeTestRule.onNodeWithText(DataSource.sideDish.first().title).performClick()
        composeTestRule.onNodeWithText(nextText).performClick()

    }

    @Test
    fun lunchTray_selectAccompanimentNGoNext_navigateToOrderSummary(){
        navigateToOrderSummary()
        navController.assertCurrentScreenName(ScreenName.Checkout.name)
    }

    private fun navigateToOrderSummary(){
        navigateToAccompaniment()
        val nextText = composeTestRule.activity.getString(R.string.next)
        composeTestRule.onNodeWithText(DataSource.accompaniments.first().title).performClick()
        composeTestRule.onNodeWithText(nextText).performClick()
    }

    @Test
    fun lunchTray_ClickCancelAtEachScreen_navigateToStart(){
        navigateToEntree()
        cancelButtonClick()

        navigateToSideDish()
        cancelButtonClick()

        navigateToAccompaniment()
        cancelButtonClick()

        navigateToOrderSummary()
        cancelButtonClick()
    }

    private fun cancelButtonClick(){
        val cancelText = composeTestRule.activity.getString(R.string.cancel)
        composeTestRule.onNodeWithText(cancelText).performClick()
        navController.assertCurrentScreenName(ScreenName.Start.name)
    }


}