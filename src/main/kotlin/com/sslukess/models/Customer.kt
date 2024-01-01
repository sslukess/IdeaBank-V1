package com.sslukess.models

import kotlinx.serialization.Serializable

@Serializable
data class Customer(val id: String, val firstName: String, val lastName: String, val email: String)

val customerStorage = mutableListOf<Customer>(
    Customer("A", "Luke", "Victor", "lukesampsear@icloud.com"),
    Customer("B", "Jess", "Schreiber", "j.f.schreiber@outlook.com"),
)