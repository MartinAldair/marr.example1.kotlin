package mx.com.marr.example1.kotlin

import io.swagger.server.config.LocalDateConverter
import io.swagger.server.config.LocalDateTimeConverter
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.ExitCodeGenerator
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@SpringBootApplication
@ComponentScan(basePackages = ["mx.com.marr.example1.kotlin", "io.swagger.server", "io.swagger.server.config"])
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

@Configuration
internal class CustomDateConfig : WebMvcConfigurerAdapter() {
	override fun addFormatters(registry: FormatterRegistry) {
		registry.addConverter(LocalDateConverter("yyyy-MM-dd"))
		registry.addConverter(LocalDateTimeConverter("yyyy-MM-dd'T'HH:mm:ss.SSS"))
	}
}

internal class ExitException : RuntimeException(), ExitCodeGenerator {

	companion object {
		private const val serialVersionUID = 1L
	}

	override fun getExitCode(): Int {
		return 10
	}
}