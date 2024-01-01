package com.sslukess

import com.sslukess.models.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.*
import kotlin.test.*

class OrderRouteTests {
    @Test
    fun testGetOrder() = testApplication {

        // This code loops through all our stored customers, and this is then compared to the response.
        customerStorage.forEach { customerToTest ->

            val response = client.get("/customer/${customerToTest.id}")
            val localCustomer = customerStorage.find { it.id == customerToTest.id } ?: return@forEach fail()

            assertEquals(
               Json.encodeToString<Customer>(Customer.serializer(), localCustomer),
                response.bodyAsText()
            )
            assertEquals(HttpStatusCode.OK, response.status)
        }
    }
}