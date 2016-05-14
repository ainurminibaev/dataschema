package ru.ainurminibaev.db.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.ainurminibaev.db.model.DBMetadata;

/**
 * Created by ainurminibaev on 11.05.16.
 */
public interface DBMetadataRepository extends MongoRepository<DBMetadata, String> {
    DBMetadata findByUrl(String host);
}
