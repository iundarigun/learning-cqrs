package br.com.devcave.cqrs.product.command.interceptor

import org.axonframework.commandhandling.CommandMessage
import org.axonframework.messaging.MessageDispatchInterceptor
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.function.BiFunction

@Component
class CreateProductCommandInterceptor : MessageDispatchInterceptor<CommandMessage<*>> {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun handle(
        messages: MutableList<out CommandMessage<*>>
    ): BiFunction<Int, CommandMessage<*>, CommandMessage<*>> {
        return BiFunction { index, command ->
            logger.info("interceptor handle, $index, $command")
            command
        }
    }
}