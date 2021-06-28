package br.com.devcave.cqrs.product.query.controller

import br.com.devcave.cqrs.product.query.FindProductsQuery
import br.com.devcave.cqrs.product.query.domain.ProductResponse
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("products")
class ProductQueryController(
    private val queryGateway: QueryGateway
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping
    fun getProducts(): List<ProductResponse> {
        val findProductsQuery = FindProductsQuery()
        return queryGateway.query(
            findProductsQuery,
            ResponseTypes.multipleInstancesOf(ProductResponse::class.java)
        ).join()
    }
}