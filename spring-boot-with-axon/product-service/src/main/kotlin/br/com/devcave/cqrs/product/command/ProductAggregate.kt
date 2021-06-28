package br.com.devcave.cqrs.product.command

import br.com.devcave.cqrs.product.command.domain.CreateProductCommand
import br.com.devcave.cqrs.product.core.event.ProductCreatedEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate
import org.slf4j.LoggerFactory
import java.math.BigDecimal

@Aggregate
class ProductAggregate() {
    private val logger = LoggerFactory.getLogger(javaClass)

    @AggregateIdentifier
    var productId: String? = null
    var title: String? = null
    var price: BigDecimal? = null
    var quantity: Int? = null

    @CommandHandler
    constructor(createProductCommand: CreateProductCommand) : this() {
        logger.info("constructor, $createProductCommand")

        // validate create product command: This parts is on request body too. Unecessary
        if (createProductCommand.price <= BigDecimal.ZERO) {
            throw IllegalArgumentException("Price cannot be less or equal than zero")
        }
        if (createProductCommand.title.isBlank()) {
            throw IllegalArgumentException("Title cannot be empty")
        }

        val event = createProductCommand.toProductCreatedEvent()

        AggregateLifecycle.apply(event)
    }

    @EventSourcingHandler
    fun on(event: ProductCreatedEvent) {
        logger.info("on, $event")
        productId = event.productId
        title = event.title
        price = event.price
        quantity = event.quantity
    }
}

private fun CreateProductCommand.toProductCreatedEvent(): ProductCreatedEvent =
    ProductCreatedEvent(
        productId = this.productId,
        title = this.title,
        price = this.price,
        quantity = this.quantity
    )
