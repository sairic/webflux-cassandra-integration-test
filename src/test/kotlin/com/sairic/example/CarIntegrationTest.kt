package com.sairic.example

import com.sairic.example.repository.domain.Car
import org.cassandraunit.spring.CassandraDataSet
import org.cassandraunit.spring.CassandraUnitDependencyInjectionTestExecutionListener
import org.cassandraunit.spring.EmbeddedCassandra
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestExecutionListeners( CassandraUnitDependencyInjectionTestExecutionListener::class, DependencyInjectionTestExecutionListener::class )
@CassandraDataSet(value = ["dataset.cql"], keyspace = "car_management")
@EmbeddedCassandra
class CarIntegrationTest {

    private val baseUrl = "/car/api/"
    private val sampleCarId = "8557f426-1cc9-41bf-a581-fadb101cd289"

    //Provided by the DependencyInjectTestExecutionListener
    @Autowired
    lateinit var webTestClient: WebTestClient

    @Test
    fun getAllCars_Success() {
        webTestClient.get().uri(baseUrl)
            .accept(MediaType.APPLICATION_JSON_UTF8)
            .exchange()
            .expectStatus().isOk
            .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
            .expectBody().consumeWith { b -> println(b) }
            .jsonPath("$.length()").isEqualTo(10)
    }

    @Test
    fun getCarById_Success() {
        webTestClient.get().uri("$baseUrl$sampleCarId")
            .accept(MediaType.APPLICATION_JSON_UTF8)
            .exchange()
            .expectStatus().isOk
            .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
            .expectBody().consumeWith { b -> println(b) }
            .jsonPath("$.id").isEqualTo(sampleCarId)
    }

    @Test
    fun getCarById_Invalid_Car_Id() {
        webTestClient.get().uri("{$baseUrl}d55ab657-7747-44ec-9f5b-c0ae77023b2c")
            .accept(MediaType.APPLICATION_JSON_UTF8)
            .exchange()
            .expectStatus().isNotFound
            .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
    }

    @Test
    fun createCar_Success() {
        webTestClient.post().uri(baseUrl)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)
            .body(Mono.just(createCar()), Car::class.java)
            .exchange()
            .expectStatus().isCreated
            .expectHeader().valueMatches("Location", "/car/api/(.*?)")
    }

    private fun createCar() = Car(UUID.randomUUID(), 2013, "Lexus", "ISF", 40100.00F)


}