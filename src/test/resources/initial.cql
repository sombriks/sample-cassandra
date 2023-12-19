create keyspace if not exists spring_cassandra
    WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'};

create table if not exists spring_cassandra.place (
    id bigint,
    address text,
    latitude double,
    longitude double,
    primary key (id)
);
