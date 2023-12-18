create keyspace if not exists spring_cassandra
    WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'};
