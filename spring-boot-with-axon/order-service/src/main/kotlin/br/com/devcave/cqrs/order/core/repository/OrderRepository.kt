package br.com.devcave.cqrs.order.core.repository

import br.com.devcave.cqrs.order.core.domain.entity.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, String>