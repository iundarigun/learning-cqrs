package br.com.devcave.cqrs.product.controller

import br.com.devcave.cqrs.product.domain.ProductRequest
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("products")
class ProductController {

    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping
    fun create(@RequestBody request: ProductRequest): String {
        logger.info("create, $request")
        return "POST OK"
    }
}