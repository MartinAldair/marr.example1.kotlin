package mx.com.marr.example1.kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["io.swagger", "mx.com.marr.example1.kotlin"])
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
