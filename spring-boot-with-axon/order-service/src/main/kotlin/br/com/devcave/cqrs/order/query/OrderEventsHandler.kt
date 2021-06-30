package br.com.devcave.cqrs.order.query

import br.com.devcave.cqrs.order.core.domain.entity.Order
import br.com.devcave.cqrs.order.core.event.OrderCreatedEvent
import br.com.devcave.cqrs.order.core.repository.OrderRepository
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.axonframework.messaging.interceptors.ExceptionHandler
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
@ProcessingGroup("order-group")
class OrderEventsHandler(
    private val orderRepository: OrderRepository
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(resultType = IllegalArgumentException::class)
    fun handle(exception: IllegalArgumentException) {
        logger.info("handle, ${exception.message}")
        throw exception
    }

    @EventHandler
    fun on(event: OrderCreatedEvent) {
        logger.info("on, $event")
        // Forcing error to see exception handler in action
        if (event.quantity == 33) {
            throw IllegalArgumentException("Quantity is 33 and can not be. Forcing exception")
        }
        orderRepository.save(event.toProduct())
    }
}

private fun OrderCreatedEvent.toProduct(): Order =
    Order(
        orderId = this.orderId,
        productId = this.productId,
        quantity = this.quantity,
        addressId = this.addressId,
        orderStatus = this.orderStatus,
        userId = this.userId
    )
