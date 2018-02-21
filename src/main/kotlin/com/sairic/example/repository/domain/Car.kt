package com.sairic.example.repository.domain

import com.datastax.driver.core.DataType
import org.springframework.data.cassandra.core.cql.PrimaryKeyType
import org.springframework.data.cassandra.core.mapping.CassandraType
import org.springframework.data.cassandra.core.mapping.Column
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn
import java.util.UUID

data class Car(@PrimaryKeyColumn(name = "car_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
               @CassandraType(type = DataType.Name.UUID) val id: UUID? = UUID.randomUUID(),
               val year: Int,
               val make: String,
               val model: String,
               val price: Float)