package br.com.devcave.cqrs.product.query

import br.com.devcave.cqrs.product.core.domain.entity.Product
import br.com.devcave.cqrs.product.core.repository.ProductRepository
import br.com.devcave.cqrs.product.query.domain.ProductResponse
import org.axonframework.queryhandling.QueryHandler
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class ProductsQueryHandler(
    private val productRepository: ProductRepository
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @QueryHandler
    fun findProducts(query: FindProductsQuery): List<ProductResponse> {
        logger.info("findProducts, $query")

        return productRepository.findAll().map {
            it.toProductResponse()
        }
    }
}

private fun Product.toProductResponse(): ProductResponse =
    ProductResponse(
        productId = this.productId,
        title = this.title,
        price = this.price,
        quantity = this.quantity
    )
