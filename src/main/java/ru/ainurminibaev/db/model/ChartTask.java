package ru.ainurminibaev.db.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import ru.ainurminibaev.db.model.enums.ChartType;
import ru.ainurminibaev.db.model.enums.PeriodType;

/**
 * Created by ainurminibaev on 20.05.16.
 */
@Document(collection = "chart_tasks")
public class ChartTask {

    public static final String NAME = "name";
    public static final String CHART_TYPE = "chart_type";
    public static final String PERIOD_TYPE = "period_type";
    public static final String PERIOD = "period";
    public static final String UPDATE_TYPE = "update_type";
    public static final String LAST_UPDATE = "last_update";
    public static final String SQL = "sql";
    public static final String DB_ID = "db_id";
    @Id
    private String id;

    @Field(NAME)
    private String name;

    @Field(DB_ID)
    private String dbId;

    @Field(CHART_TYPE)
    private ChartType chartType;

    @Field(PERIOD_TYPE)
    private PeriodType periodType;

    @Field(PERIOD)
    private int period;

    //ms
    @Field(UPDATE_TYPE)
    private long updateTime;

    @Field(LAST_UPDATE)
    private long lastUpdate;

    @Field(SQL)
    private String sql;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChartType getChartType() {
        return chartType;
    }

    public void setChartType(ChartType chartType) {
        this.chartType = chartType;
    }

    public PeriodType getPeriodType() {
        return periodType;
    }

    public void setPeriodType(PeriodType periodType) {
        this.periodType = periodType;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setLastUpdate(int lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getDbId() {
        return dbId;
    }

    public void setDbId(String dbId) {
        this.dbId = dbId;
    }
}
