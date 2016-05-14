package ru.ainurminibaev.db.dto;

import java.util.List;

/**
 * Created by ainurminibaev on 11.05.16.
 */
public class TableViewRow {
    private List<TableViewCol> columns;

    public List<TableViewCol> getColumns() {
        return columns;
    }

    public void setColumns(List<TableViewCol> columns) {
        this.columns = columns;
    }
}
