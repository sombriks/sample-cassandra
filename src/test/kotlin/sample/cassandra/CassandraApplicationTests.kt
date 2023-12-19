package sample.cassandra

import com.datastax.oss.driver.api.core.CqlSession
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.data.cassandra.core.CassandraTemplate
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@ActiveProfiles("test")
@Import(value = [TestCassandraConfig::class, CassandraConfig::class])
class CassandraApplicationTests {

    @Autowired
    private lateinit var cqlSession: CqlSession

    @Autowired
    private lateinit var repository: PlaceRepository

    @Autowired
    private lateinit var template: CassandraTemplate

    @Test
    fun shouldCount() {
        val result = template.count(Place::class.java)
        Assertions.assertNotNull(result)
    }

    @Test
    fun shouldList() {
        val result = cqlSession.execute("select * from place")
        Assertions.assertNotNull(result)
    }

    @Test
    fun shouldSave() {
		val result = repository.save(Place(address = "some street"))
        Assertions.assertNotNull(result)
    }
}
