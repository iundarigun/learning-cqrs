package br.com.devcave.cqrs.product.configuration

import br.com.devcave.cqrs.product.command.interceptor.CreateProductCommandInterceptor
import br.com.devcave.cqrs.product.core.errorhandling.ProductServiceEventErrorHandler
import org.axonframework.commandhandling.CommandBus
import org.axonframework.config.EventProcessingConfigurer
import org.springframework.context.annotation.Configuration

@Configuration
class InterceptorConfiguration(
    createProductCommandInterceptor: CreateProductCommandInterceptor,
    commandBus: CommandBus,
    eventProcessingConfigurer: EventProcessingConfigurer
) {
    init {
        commandBus.registerDispatchInterceptor(createProductCommandInterceptor)

        eventProcessingConfigurer
            .registerListenerInvocationErrorHandler("product-group") {
                // This register for the group
                ProductServiceEventErrorHandler()
            }
    }
}