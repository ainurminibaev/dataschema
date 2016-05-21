package ru.ainurminibaev.db.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.ainurminibaev.db.model.ChartTask;

/**
 * Created by ainurminibaev on 20.05.16.
 */
public interface ChartTaskRepository extends MongoRepository<ChartTask, String> {
}
