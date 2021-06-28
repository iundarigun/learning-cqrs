package br.com.devcave.cqrs.product.command.controller

import br.com.devcave.cqrs.product.command.domain.ProductRequest
import br.com.devcave.cqrs.product.command.domain.CreateProductCommand
import org.axonframework.commandhandling.gateway.CommandGateway
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("products")
class ProductCommandController(
    private val commandGateway: CommandGateway
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping
    fun create(@Valid @RequestBody request: ProductRequest): String {
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
