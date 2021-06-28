package br.com.devcave.cqrs.product.configuration

import br.com.devcave.cqrs.product.command.interceptor.CreateProductCommandInterceptor
import org.axonframework.commandhandling.CommandBus
import org.springframework.context.annotation.Configuration

@Configuration
class InterceptorConfiguration(
    createProductCommandInterceptor: CreateProductCommandInterceptor,
    commandBus: CommandBus
) {
    init {
        commandBus.registerDispatchInterceptor(createProductCommandInterceptor)
    }
}