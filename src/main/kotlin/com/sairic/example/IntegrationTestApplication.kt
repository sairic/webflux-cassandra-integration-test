package com.sairic.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class IntegrationTestApplication

fun main(args: Array<String>) {
    runApplication<IntegrationTestApplication>(*args)
}

