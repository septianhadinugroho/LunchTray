package com.example.lunchtray.model

data class OrderUiState(
    val entree: OrderItem? = null,
    val sideDish: OrderItem? = null,
    val accompaniment: OrderItem? = null,
    val itemTotalPrice: Double = 0.0,
    val orderTax: Double = 0.08,
    val orderTotalPrice: Double = 0.0
)