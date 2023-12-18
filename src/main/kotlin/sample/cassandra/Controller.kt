package sample.cassandra

import com.datastax.oss.driver.api.core.CqlSession
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("sample-cassandra")
class Controller(var cqlSession: CqlSession) {

    @GetMapping
    fun hello() = "Hello"
}
