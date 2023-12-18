package sample.cassandra

import org.springframework.boot.fromApplication
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.boot.with
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.CassandraContainer
import org.testcontainers.utility.DockerImageName

@TestConfiguration(proxyBeanMethods = false)
class TestCassandraApplication {

	@Bean
	@ServiceConnection
	fun cassandraContainer(): CassandraContainer<*> {
		return CassandraContainer(DockerImageName.parse("cassandra:latest"))
	}

}

fun main(args: Array<String>) {
	fromApplication<CassandraApplication>().with(TestCassandraApplication::class).run(*args)
}
