package br.com.devcave.cqrs.product.core.domain.entity

import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Product(
    @Id
    val productId: String,

    @Column(unique = true)
    val title: String,

    val price: BigDecimal,

    val quantity: Int
)
