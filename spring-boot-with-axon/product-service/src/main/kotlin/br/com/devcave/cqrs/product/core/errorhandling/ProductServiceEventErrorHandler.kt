package br.com.devcave.cqrs.product.core.errorhandling

import org.axonframework.eventhandling.EventMessage
import org.axonframework.eventhandling.EventMessageHandler
import org.axonframework.eventhandling.ListenerInvocationErrorHandler
import org.slf4j.LoggerFactory
import java.lang.Exception

class ProductServiceEventErrorHandler : ListenerInvocationErrorHandler {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun onError(exception: Exception, event: EventMessage<*>, eventHandler: EventMessageHandler) {
        logger.info("onError, type=${exception.javaClass.simpleName}, message=${exception.message}")
        // Throwing the exception, we will force rollback on the transactions
        throw exception
    }
}