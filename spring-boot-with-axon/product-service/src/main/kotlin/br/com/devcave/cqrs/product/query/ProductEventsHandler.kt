package br.com.devcave.cqrs.product.query

import br.com.devcave.cqrs.product.core.domain.entity.Product
import br.com.devcave.cqrs.product.core.event.ProductCreatedEvent
import br.com.devcave.cqrs.product.core.repository.ProductRepository
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.axonframework.messaging.interceptors.ExceptionHandler
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
@ProcessingGroup("product-group")
class ProductEventsHandler(
    private val productRepository: ProductRepository
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(resultType = IllegalArgumentException::class)
    fun handle(exception: IllegalArgumentException) {
        logger.info("handle, ${exception.message}")
        throw exception
    }

    @EventHandler
    fun on(event: ProductCreatedEvent) {
        logger.info("on, $event")
        // Forcing error to see exception handler in action
        if (event.price.toLong() == 33L) {
            throw IllegalArgumentException("Price is 33 and can not be. Forcing exception")
        }
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
