package mx.com.marr.example1.kotlin

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.ExitCodeGenerator
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["mx.com.marr.example1.kotlin", "io.swagger", "io.swagger.config"])
class Application : CommandLineRunner {
	override fun run(vararg arg0: String?) {
		if (arg0.size > 0 && arg0[0] == "exitcode") {
			throw ExitException()
		}
	}
}

fun main(args: Array<String>) {
	SpringApplication.run(Application::class.java, *args)
}

internal class ExitException : RuntimeException(), ExitCodeGenerator {

	companion object {
		private const val serialVersionUID = 1L
	}

	override fun getExitCode(): Int {
		return 10
	}
}