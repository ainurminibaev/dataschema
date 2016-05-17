package ru.ainurminibaev.db.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by ainurminibaev on 15.05.16.
 */
@Document(collection = "table_settings")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TableSettings {
    @Id
    @JsonIgnore
    private String id;

    @JsonProperty("table_name")
    @Field("table_name")
    private String tableName;

    private String printable;

    @JsonIgnore
    @Field("db_id")
    private String dbId;

    @JsonProperty("str_cols")
    @Field("str_cols")
    private List<String> strCols;

    private List<ColumnSettings> columns;

    private Boolean visible;

    public List<String> getStrCols() {
        return strCols;
    }

    public void setStrCols(List<String> strCols) {
        this.strCols = strCols;
    }

    public List<ColumnSettings> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnSettings> columns) {
        this.columns = columns;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getPrintable() {
        return printable;
    }

    public void setPrintable(String printable) {
        this.printable = printable;
    }
}
