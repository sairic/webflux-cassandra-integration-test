package com.sairic.example.repository.domain

import org.springframework.data.cassandra.core.mapping.Column
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import java.util.UUID

data class Car(@Column("car_id") @PrimaryKey val carId: UUID?,
               val year: Int,
               val make: String,
               val model: String,
               val price: Float)