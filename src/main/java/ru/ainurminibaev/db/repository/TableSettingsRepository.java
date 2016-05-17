package ru.ainurminibaev.db.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ru.ainurminibaev.db.model.TableMetadata;
import ru.ainurminibaev.db.model.TableSettings;

/**
 * Created by ainurminibaev on 15.05.16.
 */
public interface TableSettingsRepository extends MongoRepository<TableSettings, String> {
    @Query("{'dbId': ?1, 'tableName':?0}")
    TableSettings findByTableAndDb(String tableName, String db);

    @Query("{ 'dbId':?0 }")
    List<TableSettings> findAllTables(String db);
}
