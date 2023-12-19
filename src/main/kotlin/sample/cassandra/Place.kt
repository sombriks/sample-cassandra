package sample.cassandra

import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table

@Table
class Place(
    @PrimaryKey
    var id: Long = System.currentTimeMillis(),
    var address: String? = null,
    var latitude: Double? = null,
    var longitude: Double? = null
)
