package sample.cassandra

import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.cql.ResultSet
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.data.cassandra.core.CassandraTemplate
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

@ActiveProfiles("test")
@WebMvcTest(PlaceController::class)
@Import(value = [TestCassandraConfig::class, WebConfig::class])
class PlaceControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var cqlSession: CqlSession

    @MockBean
    private lateinit var template: CassandraTemplate

    @MockBean
    private lateinit var repository: PlaceRepository

    @Mock
    private lateinit var resultSet: ResultSet

    @BeforeEach
    fun setup () {
        Mockito.`when`(cqlSession.execute(anyString()))
            .thenReturn(resultSet)
        Mockito.`when`(resultSet.all())
            .thenReturn(emptyList())
    }

    private fun req(uri: String) = MockMvcRequestBuilders.get(uri)

    @Test
    fun shouldListByRepository() {
        val result = mockMvc.perform(req("/places")).andReturn()
        Assertions.assertNotNull(result)
        Assertions.assertEquals(200, result.response.status)
    }

    @Test
    fun shouldListByCqlSession() {
        val uri = "/places/query-session?q=select * from place"
        val result = mockMvc.perform(req(uri)).andReturn()
        Assertions.assertNotNull(result)
        Assertions.assertEquals(200, result.response.status)
    }

    @Test
    fun shouldListByCassandraTemplate() {
        val uri = "/places/query-template?q=select * from place"
        val result = mockMvc.perform(req(uri)).andReturn()
        Assertions.assertNotNull(result)
        Assertions.assertEquals(200, result.response.status)
    }
}

