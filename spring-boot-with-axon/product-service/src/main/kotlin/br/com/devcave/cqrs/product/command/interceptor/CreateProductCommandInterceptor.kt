package br.com.devcave.cqrs.product.command.interceptor

import br.com.devcave.cqrs.product.command.domain.CreateProductCommand
import br.com.devcave.cqrs.product.command.repository.ProductLookupRepository
import org.axonframework.commandhandling.CommandMessage
import org.axonframework.messaging.MessageDispatchInterceptor
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.function.BiFunction

@Component
class CreateProductCommandInterceptor(
    private val productLookupRepository: ProductLookupRepository
) : MessageDispatchInterceptor<CommandMessage<*>> {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun handle(
        messages: MutableList<out CommandMessage<*>>
    ): BiFunction<Int, CommandMessage<*>, CommandMessage<*>> {
        return BiFunction { index, command ->
            logger.info("interceptor handle, $index, $command")
            if (command.payload is CreateProductCommand) {
                val createProductCommand = command.payload as CreateProductCommand
                if (productLookupRepository.existsByProductIdOrTitle(
                        createProductCommand.productId,
                        createProductCommand.title
                    )
                ) {
                    throw IllegalStateException("Product already exists")
                }
            }
            command
        }
    }
}