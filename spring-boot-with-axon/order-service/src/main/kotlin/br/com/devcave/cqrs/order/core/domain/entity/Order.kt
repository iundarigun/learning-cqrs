package br.com.devcave.cqrs.order.core.domain.entity

import br.com.devcave.cqrs.order.command.domain.OrderStatus
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "`order`")
data class Order(
    @Id
    val orderId: String,
    val productId: String,
    val quantity: Int,
    val addressId: String,
    @Enumerated(EnumType.STRING)
    val orderStatus: OrderStatus,
    val userId: String

)
