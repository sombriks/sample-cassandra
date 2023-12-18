package sample.cassandra

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Import

@SpringBootTest
@Import(value = [TestCassandraApplication::class])
class CassandraApplicationTests {

	@Test
	fun contextLoads() {
	}

}
