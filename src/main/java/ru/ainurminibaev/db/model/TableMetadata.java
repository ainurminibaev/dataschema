package ru.ainurminibaev.db.model;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import ru.ainurminibaev.db.model.enums.DBDataType;

/**
 * Created by ainurminibaev on 11.05.16.
 */
@Document(collection = "table_metadata")
public class TableMetadata {

    @Id
    private String id;

    @Field("table_name")
    private String tableName;

    @Field("db_id")
    private String dbId;

    private List<String> columns;

    @Field("column_types")
    private Map<String, DBDataType> columnTypes;

    private Map<String, ReferenceTable> columnsReference;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDbId() {
        return dbId;
    }

    public void setDbId(String dbId) {
        this.dbId = dbId;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public Map<String, DBDataType> getColumnTypes() {
        return columnTypes;
    }

    public void setColumnTypes(Map<String, DBDataType> columnTypes) {
        this.columnTypes = columnTypes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, ReferenceTable> getColumnsReference() {
        return columnsReference;
    }

    public void setColumnsReference(Map<String, ReferenceTable> columnsReference) {
        this.columnsReference = columnsReference;
    }
}
