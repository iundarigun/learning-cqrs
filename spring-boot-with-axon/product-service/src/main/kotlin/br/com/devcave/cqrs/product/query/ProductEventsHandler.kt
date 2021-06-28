package br.com.devcave.cqrs.product.query

import br.com.devcave.cqrs.product.core.domain.entity.Product
import br.com.devcave.cqrs.product.core.event.ProductCreatedEvent
import br.com.devcave.cqrs.product.core.repository.ProductRepository
import org.axonframework.eventhandling.EventHandler
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class ProductEventsHandler(
    private val productRepository: ProductRepository
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @EventHandler
    fun on(event: ProductCreatedEvent) {
        logger.info("on, $event")
        productRepository.save(event.toProduct())
    }
}

private fun ProductCreatedEvent.toProduct(): Product =
    Product(
        productId = this.productId,
        title = this.title,
        price = this.price,
        quantity = this.quantity
    )
