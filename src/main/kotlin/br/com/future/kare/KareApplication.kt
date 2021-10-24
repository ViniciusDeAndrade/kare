package br.com.future.kare

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KareApplication

fun main(args: Array<String>) {
	runApplication<KareApplication>(*args)
}
