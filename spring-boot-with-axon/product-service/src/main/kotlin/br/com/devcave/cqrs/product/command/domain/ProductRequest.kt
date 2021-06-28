package br.com.devcave.cqrs.product.command.domain

import java.math.BigDecimal
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Positive

data class ProductRequest(
    @field:NotBlank(message = "product title can not be empty")
    val title: String,

    @field:Min(value = 1, message = "Price cannot be lower than one")
    val price: BigDecimal,

    @field:Positive(message = "Quantity can not be negative")
    @field:Max(value = 5, message = "Quantity can not be big than 5")
    val quantity: Int
)