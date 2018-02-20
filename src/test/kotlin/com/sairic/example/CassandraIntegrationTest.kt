package com.sairic.example

import org.cassandraunit.spring.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.util.UUID


@RunWith(SpringJUnit4ClassRunner::class)
@TestPropertySource(locations = ["classpath:application.properties"])
@ContextConfiguration
@EnableAutoConfiguration
@TestExecutionListeners( CassandraUnitTestExecutionListener::class, CassandraUnitDependencyInjectionTestExecutionListener::class, DependencyInjectionTestExecutionListener::class )
@ComponentScan(basePackages = ["com.sairic.example"])
@CassandraDataSet(value = ["dataset.cql"], keyspace = "car_management")
@EmbeddedCassandra
class CassandraIntegrationTest {

    private val configId = UUID.fromString("8fc8764f-0d44-4145-abe0-bb4e064d78e7")
    private val client = "ESPN"
    private val subAccount = "watchESPN"

    /*@Autowired
    lateinit var configurationRepo: ReactiveConfigurationRepository

    @Test
    fun testGetConfigurationByClient() {
        val configFlux = configurationRepo.findConfigurationByClient(Mono.just(client))

        StepVerifier.create(configFlux)
            .expectNextMatches { config ->
                config.pk.id == configId && config.pk.client == client && config.name == "default_configuration"
            }
            .expectComplete()
            .verify()
    }*/

}