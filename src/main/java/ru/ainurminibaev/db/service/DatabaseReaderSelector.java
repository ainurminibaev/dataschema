package ru.ainurminibaev.db.service;

/**
 * Created by ainurminibaev on 18.05.16.
 */
public interface DatabaseReaderSelector {
    DatabaseReader getDbReader(String dbId);
}
