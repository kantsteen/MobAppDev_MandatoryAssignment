package com.example.mobappdev_mandatoryassignment

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.filterToOne
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import com.example.mobappdev_mandatoryassignment.model.SalesItem
import com.example.mobappdev_mandatoryassignment.screens.SalesItemList
import kotlinx.coroutines.withTimeout
import okhttp3.internal.wait
import org.junit.Rule
import org.junit.Test
import kotlin.collections.listOf

class Test {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun delete_item1() {
        rule.setContent {

            val salesItemsToDelete = listOf(
                SalesItem(1, "Test Item 1", 100, "test1@test.com", "11111111", 12345),
                SalesItem(2, "Test Item 2", 200, "test2@test.com", "22222222", 12345)
            )

            var salesItems by remember { mutableStateOf(salesItemsToDelete) }

            SalesItemList(
                salesItems = salesItems,
                errorMessage = "",
                modifier = Modifier,
                authViewModel = null,
                onSalesItemSelected = {},
                onSalesItemDeleted = { itemToDelete ->
                    salesItems = salesItems.filter { it.id != itemToDelete.id }
                },
                onSalesItemsReload = {},
                salesItemsLoading = false,
                onAdd = {},
                onLoginClick = {},
                onLogoutClick = {},
                sortByTitle = {},
                sortByPrice = {},
                filterByTitle = {},
                filterByPrice = {},
                currentEmail = "test1@test.com"
            )
        }
        val testItem1 = "Test Item 1"
        val delButtonTag = "delete_item_button"
        val delButton = rule.onNodeWithTag((delButtonTag))

        // find test item 1 and assert it exists
        val item1 = rule.onNode(hasText(testItem1, substring = true)).assertExists()
        item1.assertExists()

        // find delete button within item1
        val deleteButtonOnItem1 = item1.onChildren().filterToOne(hasTestTag(delButtonTag))

        // click delete button
        deleteButtonOnItem1.performClick()

        // assert item1 has been deleted
        item1.assertDoesNotExist()
    }


    // TODO
    @Test
    fun add_item3() {
        rule.setContent {

            val salesItems = listOf(
                SalesItem(1, "Test Item 1", 100, "test1@test.com", "11111111", 12345),
                SalesItem(2, "Test Item 2", 200, "test2@test.com", "22222222", 12345)
            )

            SalesItemList(
                salesItems = salesItems,
                errorMessage = "",
                modifier = Modifier,
                authViewModel = null,
                onSalesItemSelected = {},
                onSalesItemDeleted = { },
                onSalesItemsReload = {},
                salesItemsLoading = false,
                onAdd = {},
                onLoginClick = {},
                onLogoutClick = {},
                sortByTitle = {},
                sortByPrice = {},
                filterByTitle = {},
                filterByPrice = {},
                currentEmail = "test1@test.com"
            )        }
    }
}