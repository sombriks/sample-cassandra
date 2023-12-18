package sample.cassandra

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.CassandraContainer
import org.testcontainers.utility.DockerImageName

@TestConfiguration(proxyBeanMethods = false)
class TestCassandraConfig {

	@Bean
	@ServiceConnection
	fun cassandraContainer(): CassandraContainer<*> {
		return CassandraContainer(DockerImageName
			.parse("cassandra:4.1.3"))
			.withInitScript("initial.cql")
	}
}

