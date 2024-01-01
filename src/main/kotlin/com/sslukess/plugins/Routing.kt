package com.sslukess.plugins

import com.sslukess.routes.customerRouting
import com.sslukess.routes.orderRouting
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        customerRouting()
        orderRouting()
    }
}
