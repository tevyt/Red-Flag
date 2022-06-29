package me.travisalexandersmith.redflag

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RedFlagApplication

fun main(args: Array<String>) {
	runApplication<RedFlagApplication>(*args)
}
