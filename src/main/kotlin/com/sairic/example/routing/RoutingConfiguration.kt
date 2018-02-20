package com.sairic.example.routing

import com.sairic.example.repository.CarRepository

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.*
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono
import java.net.URI


@Configuration
class RoutingConfiguration {

    @Bean
    fun routerFunction(carRepository: CarRepository): RouterFunction<ServerResponse> = router {
        ("/car/api").nest {
            GET("/") { ok().body(carRepository.findAll())
            }
            GET("/{carId}") { req ->
                val carId = req.pathVariable("carId")
                ok().body(carRepository.findById(carId))
            }
        }
    }
}

           /* ("/configuration").nest {
                GET("/{client}") { req ->
                    val client = req.pathVariable("client")
                    ok().header("Access-Control-Allow-Origin", "*").
                        header("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS")
                        .body(handler.getAllConfigurationForClient(client))
                }
                GET("/details/{configId}") { req ->
                    val configId = req.pathVariable("configId")

                    ok().header("Access-Control-Allow-Origin", "*").
                        header("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS")
                        .body(handler.getConfigurationDetails(configId))
                }
                PATCH("/details/{client}/{configId}/{name}") { req ->
                    val updateConfiguration = req.bodyToMono(UpdateConfigurationDetails::class.java)
                    val configId = req.pathVariable("configId")
                    val name = req.pathVariable("name")
                    val clientId = req.pathVariable("client")
                    handler.updateConfigurationDetails(clientId, configId, name, updateConfiguration)
                        .flatMap { successful ->
                            if(successful) {
                                accepted().header("Access-Control-Allow-Origin", "*")
                                    .header("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS")
                                    .build()
                            } else {
                                badRequest().header("Access-Control-Allow-Origin", "*")
                                    .header("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS")
                                    .build()
                            }
                        }
                }
                POST("/") { req ->
                    val createConfigRequest = req.bodyToMono(CreateConfiguration::class.java)
                    handler.createConfiguration(createConfigRequest)
                        .flatMap { config ->
                            ServerResponse.created(URI.create("/configuration/${config.id}"))
                                .header("Access-Control-Allow-Origin", "*")
                                .header("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS")
                                .build()
                        }
                }
                PUT("/") { req ->
                    val updateConfigRequest = req.bodyToMono(UpdateConfiguration::class.java)
                    handler.updateConfiguration(updateConfigRequest)
                        .flatMap { successful ->
                            if(successful) {
                                noContent().header("Access-Control-Allow-Origin", "*")
                                    .header("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS")
                                    .build()
                            } else {
                                badRequest().header("Access-Control-Allow-Origin", "*")
                                    .header("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS")
                                    .build()
                            }
                        }
                }
                DELETE("/{client}/{configId}") { req ->
                    val reqParams = Mono.just(Pair(req.pathVariable("configId"), req.pathVariable("client")))
                    handler.deleteConfiguration(reqParams)
                        .flatMap {successful ->
                            if(successful) {
                                noContent().header("Access-Control-Allow-Origin", "*")
                                    .header("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS")
                                    .build()
                            } else {
                                badRequest().header("Access-Control-Allow-Origin", "*")
                                    .header("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS")
                                    .build()
                            }
                        }
                }
            }
        }

    }

}*/