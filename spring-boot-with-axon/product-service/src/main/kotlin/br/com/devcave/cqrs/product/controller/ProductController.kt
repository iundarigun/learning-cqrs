package br.com.devcave.cqrs.product.controller

import br.com.devcave.cqrs.product.domain.ProductRequest
import br.com.devcave.cqrs.product.domain.command.CreateProductCommand
import org.axonframework.commandhandling.gateway.CommandGateway
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("products")
class ProductController(
    private val commandGateway: CommandGateway
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping
    fun create(@RequestBody request: ProductRequest): String {
        logger.info("create, $request")
        val createProductCommand = request.toCreateProductCommand()

        val result = commandGateway.sendAndWait<String>(createProductCommand)

        return "POST OK: $result"
    }
}

private fun ProductRequest.toCreateProductCommand(): CreateProductCommand =
    CreateProductCommand(
        productId = UUID.randomUUID().toString(),
        title = this.title,
        price = this.price,
        quantity = this.quantity
    )
