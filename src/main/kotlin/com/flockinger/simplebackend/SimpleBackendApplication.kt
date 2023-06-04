package com.flockinger.simplebackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SimpleBackendApplication

fun main(args: Array<String>) {
	runApplication<SimpleBackendApplication>(*args)
}
