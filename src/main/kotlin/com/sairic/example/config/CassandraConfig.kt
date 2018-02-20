package com.sairic.example.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.data.cassandra.config.AbstractReactiveCassandraConfiguration
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories

@Configuration
@EnableReactiveCassandraRepositories
class CassandraConfig: AbstractReactiveCassandraConfiguration() {

    @Value("\${spring.data.cassandra.port}")
    var port: String? = null

    @Value("\${spring.data.cassandra.contact-points}")
    var host: String? = null

    override fun getKeyspaceName() = "car_management"

    override fun getContactPoints(): String = host!!

    override fun getPort(): Int = Integer.valueOf(port!!)
}