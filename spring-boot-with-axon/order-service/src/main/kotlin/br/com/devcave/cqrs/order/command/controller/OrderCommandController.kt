package br.com.devcave.cqrs.order.command.controller

import br.com.devcave.cqrs.order.command.domain.CreateOrderCommand
import br.com.devcave.cqrs.order.command.domain.OrderRequest
import org.axonframework.commandhandling.gateway.CommandGateway
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("orders")
class OrderCommandController(
    private val commandGateway: CommandGateway
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping
    fun createOrder(@RequestBody request: OrderRequest): String {

        logger.info("create, $request")
        val createProductCommand = request.toCreateOrderCommand()

        val result = commandGateway.sendAndWait<String>(createProductCommand)

        return "Ok: $result"
    }
}

private fun OrderRequest.toCreateOrderCommand(): CreateOrderCommand =
    CreateOrderCommand(
        orderId = UUID.randomUUID().toString(),
        productId = this.productId,
        quantity = this.quantity,
        addressId = this.addressId
    )