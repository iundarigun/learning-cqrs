package br.com.devcave.cqrs.product.command.domain.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class ProductLookup(
    @Id
    val productId: String,

    @Column(unique = true)
    val title: String
)
