package com.sairic.example.repository

import com.sairic.example.repository.domain.Car
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository

interface CarRepository: ReactiveCassandraRepository<Car, String>