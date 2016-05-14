package ru.ainurminibaev.db.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ru.ainurminibaev.db.model.TableMetadata;

/**
 * Created by ainurminibaev on 11.05.16.
 */
public interface TableMetadataRepository extends MongoRepository<TableMetadata, String> {

    //    @Query("SELECT tm FROM TableMetadata tm WHERE tm.tableName=:tableName AND tm.dbId=:dbId")
    @Query("{'dbId': ?1, 'tableName':?0}")
    TableMetadata findByTableAndDb(String tableName, String db);

    //    @Query("SELECT tm FROM TableMetadata tm WHERE  tm.dbId=:dbId")
    @Query("{ 'dbId':?0 }")
    List<TableMetadata> findAllTables(String db);
}
