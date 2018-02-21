package com.sairic.example.repository

import com.sairic.example.repository.domain.Car
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository
import reactor.core.publisher.Mono
import java.util.*

interface CarRepository: ReactiveCassandraRepository<Car, String> {

    fun findOneById(id: UUID): Mono<Car>
}