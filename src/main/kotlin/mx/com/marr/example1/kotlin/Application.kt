package mx.com.marr.example1.kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["mx.com.marr.example1.kotlin", "io.swagger", "io.swagger.config"])
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
