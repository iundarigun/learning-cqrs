package br.com.devcave.cqrs.product.command

import br.com.devcave.cqrs.product.command.domain.entity.ProductLookup
import br.com.devcave.cqrs.product.command.repository.ProductLookupRepository
import br.com.devcave.cqrs.product.core.domain.entity.Product
import br.com.devcave.cqrs.product.core.event.ProductCreatedEvent
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
@ProcessingGroup("product-group")
class ProductLookupEventsHandler(
    private val productLookupRepository: ProductLookupRepository
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @EventHandler
    fun on(event: ProductCreatedEvent) {
        logger.info("on, $event")
        // Forcing error to see exception handler in action
        if (event.price.toLong() == 43L) {
            throw IllegalArgumentException("Price is 43 and can not be. Forcing exception")
        }
        productLookupRepository.save(event.toProductLookup())
    }
}

private fun ProductCreatedEvent.toProductLookup(): ProductLookup =
    ProductLookup(
        productId = this.productId,
        title = this.title
    )
