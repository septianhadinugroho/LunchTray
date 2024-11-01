package com.example.lunchtray

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.lunchtray.data.DataSource
import org.junit.Rule
import org.junit.Test

class LunchTrayUITest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun selectOptions_verifyContent(){
        val itemList = DataSource.entree
        composeTestRule.setContent {
            ChooseScreen(
                list = itemList,
                onCancel = { /*TODO*/ },
                isEnabledNextButton = false,
                onNext = { /*TODO*/ },
                onSelected = {},
                currentSelectedItem = null,
            )
        }
        repeat(itemList.size){ index ->
            composeTestRule.onNodeWithText(itemList[index].title).assertExists()
        }
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.next)).assertIsNotEnabled()

    }
}