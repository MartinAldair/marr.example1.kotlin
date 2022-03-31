package mx.com.marr.example1.kotlin

import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ApplicationTests {

	private val log: Logger = LoggerFactory.getLogger(this.javaClass)

	@Value("\${swagger.ui.project.title}")
	private val swaggerUiProjectTitle: String? = null;

	@Test
	fun contextLoads() {
		log.info("swaggerUiProjectTitle $swaggerUiProjectTitle");
		val x1 = 8;
		val x2 = 5;
		val t = x1 + x2;
		log.info("" +t );
	}

}
