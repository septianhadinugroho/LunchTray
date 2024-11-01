package com.example.lunchtray

import com.example.lunchtray.data.DataSource
import org.junit.Assert.*
import org.junit.Test

class OrderViewModelTest{
    private val viewModel = OrderViewModel()

    @Test
    fun orderViewModel_Initialization_OnlyTaxIsAssgined(){
        val currentState = viewModel.uiState.value

        assertNull(currentState.entree)
        assertNull(currentState.sideDish)
        assertNull(currentState.accompaniment)
        assertTrue(currentState.orderTax == 0.08)
        assertTrue(currentState.orderTotalPrice == 0.0)
        assertTrue(currentState.itemTotalPrice == 0.0)
    }

    @Test
    fun orderViewModel_SelectEntree_AssignEntreeAndCalculatePrice(){
        val entree = DataSource.entree.first()
        viewModel.updateEntree(entree)

        val currentState = viewModel.uiState.value
        assertNotNull(currentState.entree)
        assertTrue(currentState.itemTotalPrice == entree.price)
        assertTrue(currentState.orderTotalPrice == entree.price+currentState.orderTax)
    }

    @Test
    fun orderViewModel_SelectEntreeAndSideDish_AssginBothAndCalculatePrice(){
        val  entree = DataSource.entree.first()
        val sideDish = DataSource.sideDish.first()
        viewModel.updateEntree(entree)
        viewModel.updateSideDish(sideDish)

        val currentState = viewModel.uiState.value
        assertNotNull(currentState.entree)
        assertNotNull(currentState.sideDish)
        assertTrue(currentState.itemTotalPrice == entree.price+sideDish.price)
        assertTrue(currentState.orderTotalPrice == entree.price+sideDish.price+ currentState.orderTax)
    }

    @Test
    fun orderViewModel_SelectAll_AssginAllAndCalculatePrice(){
        val  entree = DataSource.entree.first()
        val sideDish = DataSource.sideDish.first()
        val accompaniment = DataSource.accompaniments.first()
        viewModel.updateEntree(entree)
        viewModel.updateSideDish(sideDish)
        viewModel.updateAccompaniment(accompaniment)

        val currentState = viewModel.uiState.value
        val totalPrice = entree.price+sideDish.price+accompaniment.price
        assertNotNull(currentState.entree)
        assertNotNull(currentState.sideDish)
        assertNotNull(currentState.accompaniment)
        assertTrue(currentState.itemTotalPrice == totalPrice)
        assertTrue(currentState.orderTotalPrice == totalPrice + currentState.orderTax)
    }

    @Test
    fun orderViewModel_ResetState_AssignInitialState(){
        viewModel.reset()
        val currentState = viewModel.uiState.value
        assertTrue(currentState==viewModel.uiState.value)
    }
}