package sample.cassandra

import com.datastax.oss.driver.api.core.CqlSession
import org.springframework.data.cassandra.core.CassandraTemplate
import org.springframework.data.cassandra.core.cql.CqlTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("places")
class PlaceController(
    var cqlSession: CqlSession,
    var template: CassandraTemplate,
    var repository: PlaceRepository
) {

    @GetMapping("query-session")
    fun querySession(q: String) = cqlSession.execute(q).all()

    @GetMapping("query-template")
    fun queryTemplate(q: String) = template.select(q, Place::class.java)

    @GetMapping
    fun gueryRepository() = repository.findAll()
}
