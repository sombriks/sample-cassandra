package sample.cassandra

import com.datastax.oss.driver.api.core.CqlSession
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@SpringBootTest
@Import(value = [TestCassandraConfig::class])
class CassandraApplicationTests {

	@Autowired
	private lateinit var cqlSession: CqlSession

	@Test
	fun contextLoads() {
	}

}
