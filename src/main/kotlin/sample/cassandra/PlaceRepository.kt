package sample.cassandra

import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.stereotype.Repository

@Repository
interface PlaceRepository : CassandraRepository<Place, Long> {
}
