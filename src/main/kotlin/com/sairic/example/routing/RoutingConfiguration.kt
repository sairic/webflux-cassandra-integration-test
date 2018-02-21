package com.sairic.example.routing

import com.sairic.example.repository.CarRepository
import com.sairic.example.repository.domain.Car
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.*
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono
import java.net.URI
import java.util.UUID


@Configuration
class RoutingConfiguration {

    @Autowired
    lateinit var carRepository: CarRepository

    @Bean
    fun routerFunction(): RouterFunction<ServerResponse> = router {
        ("/car/api").nest {
            GET("/") { ok().body(carRepository.findAll()) }
            GET("/{carId}") { req ->
                val carId = req.pathVariable("carId")
                carRepository.findOneById(UUID.fromString(carId)).flatMap { car ->
                   ok().body(Mono.just(car))
                }.switchIfEmpty(notFound().build())

            }
            POST("/") { req ->
                req.bodyToMono(Car::class.java).flatMap { body ->
                    carRepository.save<Car>(body).flatMap { car ->
                        created(URI.create("/car/api/${car.id}")).build()
                    }
                }
            }
        }
    }
}