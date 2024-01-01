package com.sslukess.routes

import com.sslukess.models.Order
import com.sslukess.models.customerStorage
import com.sslukess.models.orderStorage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.orderRouting() {

    route("/order") {
        get {
            if (orderStorage.isNotEmpty()) {
                call.respond(orderStorage)
            } else {
                call.respondText("No orders", status = HttpStatusCode.NotFound)
            }
        }
        get("{id}") {
            val reqOrderId = call.parameters["id"] ?: call.respondText("Sorry, that is a badly formed ID", status = HttpStatusCode.BadRequest)

            val order = orderStorage.find { order -> order.orderId == reqOrderId } ?: return@get call.respondText("Order not found", status = HttpStatusCode.NotFound)

            // ok now the order exists.
            call.respond(order as Order)
        }
        get("{id}/total") {
            val reqOrderId = call.parameters["id"] ?: call.respondText("Sorry, that is a badly formed ID", status = HttpStatusCode.BadRequest)

            val order = orderStorage.find { order -> order.orderId == reqOrderId } ?: return@get call.respondText("Order not found", status = HttpStatusCode.NotFound)

            val orderTotal = order.content.sumOf { it.price * it.amount}

            call.respond(orderTotal)
        }
        get("/customer/{customerId}") {
            val customerId = call.parameters["customerId"] ?: return@get call.respondText("Sorry that is not a valid customer ID", status = HttpStatusCode.BadRequest)

            val customer = customerStorage.find { it.id == customerId} ?: return@get call.respondText("That customer does not exist", status = HttpStatusCode.NotFound)

            val customersOrders = orderStorage.filter { it.customerId.id == customer.id }

            if (customersOrders.isNotEmpty()) return@get call.respond(customersOrders)

            return@get call.respondText("That customer has no orders :(", status = HttpStatusCode.NotFound)
        }
    }
}