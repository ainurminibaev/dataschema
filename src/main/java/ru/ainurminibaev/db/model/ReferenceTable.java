package ru.ainurminibaev.db.model;

/**
 * Created by ainurminibaev on 19.05.16.
 */
public class ReferenceTable {

    private String tableName;

    private String columnName;

    public ReferenceTable(String refTableName, String refColumnName) {
        this.tableName = refTableName;
        this.columnName = refColumnName;
    }

    public ReferenceTable() {
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
}
