package ru.ainurminibaev.db.model;

import javax.swing.table.TableModel;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by ainurminibaev on 20.05.16.
 */
@Document(collection = "chart_results")
public class ChartResult {

    @Id
    private String id;

    private String chartId;

    private long executionTime;

    private String imageLink;

    private Map<String, Integer> dataMap;
    private TableModel dataTable;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChartId() {
        return chartId;
    }

    public void setChartId(String chartId) {
        this.chartId = chartId;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    public Map<String, Integer> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, Integer> dataMap) {
        this.dataMap = dataMap;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public void setDataTable(TableModel dataTable) {
        this.dataTable = dataTable;
    }

    public TableModel getDataTable() {
        return dataTable;
    }
}
