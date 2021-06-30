package br.com.devcave.cqrs.order.saga

import br.com.devcave.cqrs.core.commands.ReserveProductCommand
import br.com.devcave.cqrs.order.core.event.OrderCreatedEvent
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.modelling.saga.SagaEventHandler
import org.axonframework.modelling.saga.StartSaga
import org.axonframework.spring.stereotype.Saga
import org.slf4j.LoggerFactory

@Saga
class OrderSaga(
    @Transient
    private val commandGateway: CommandGateway
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    fun handle(orderCreatedEvent: OrderCreatedEvent) {
        val reserveProductCommand = orderCreatedEvent.toReserveProductCommand()

        commandGateway.send<ReserveProductCommand, String>(reserveProductCommand) { commandMessage, resultMessage ->
            logger.info("callback for $commandMessage")
            if (resultMessage.isExceptional) {
                logger.info("we need to start a compensation transaction")
                // Start a compensation transaction
            }
        }
    }
}

private fun OrderCreatedEvent.toReserveProductCommand(): ReserveProductCommand =
    ReserveProductCommand(
        productId = this.productId,
        quantity = this.quantity,
        orderId = this.orderId,
        userId = this.userId
    )
