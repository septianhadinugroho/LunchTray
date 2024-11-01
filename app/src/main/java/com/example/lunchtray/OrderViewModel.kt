package com.example.lunchtray

import androidx.lifecycle.ViewModel
import com.example.lunchtray.model.OrderItem
import com.example.lunchtray.model.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OrderViewModel: ViewModel(){
    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState :StateFlow<OrderUiState> = _uiState.asStateFlow()

    private fun calculatePrice(currentState: OrderUiState):OrderUiState{
        var itemTotalPrice = 0.0

        if(currentState.entree != null){
            itemTotalPrice += currentState.entree.price
        }
        if(currentState.sideDish != null){
            itemTotalPrice += currentState.sideDish.price
        }
        if(currentState.accompaniment != null){
            itemTotalPrice += currentState.accompaniment.price
        }

        var orderTotalPrice = itemTotalPrice + currentState.orderTax


        return currentState.copy(
                itemTotalPrice = itemTotalPrice,
                orderTotalPrice = orderTotalPrice
            )

    }

    fun updateEntree(entree: OrderItem){
        _uiState.update { currentState->
            calculatePrice(currentState.copy(
                entree = entree
            ))
        }

    }

    fun updateSideDish(sideDish: OrderItem){
        _uiState.update { currentState->

            calculatePrice(currentState.copy(
                sideDish = sideDish
            ))
        }

    }

    fun updateAccompaniment(accompaniment: OrderItem){
        _uiState.update { currentState->
            calculatePrice(currentState.copy(
                accompaniment = accompaniment
            ))
        }

    }


    fun reset(){
        _uiState.value = OrderUiState()
    }
}