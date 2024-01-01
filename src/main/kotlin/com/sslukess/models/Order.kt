package com.sslukess.models

import kotlinx.serialization.Serializable

@Serializable
data class Order(val orderId: String, val orderDate: String, val content: List< OrderItem>, val customerId: Customer)

@Serializable
data class OrderItem(val item: String, val amount: Int, val price: Double)

val orderStorage = listOf<Order>(
    Order(
        "O1",
        "2023-12-30",
        listOf(
            OrderItem("Coke", 1, 2.99),
            OrderItem("Pepsi", 1, 1.99),
            OrderItem("Solo", 2, 2.89),
            OrderItem("Fanta", 8, 2.59),
        ),
        customerStorage[0]
    ),
    Order(
        "O2",
        "2023-12-28",
        listOf(
            OrderItem("Coke", 2, 2.99),
            OrderItem("Pepsi", 5, 1.99),
            OrderItem("Solo", 2, 2.89),
        ),
        customerStorage[1]
    ),
)
