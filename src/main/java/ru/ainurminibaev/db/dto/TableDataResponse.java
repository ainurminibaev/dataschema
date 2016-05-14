package ru.ainurminibaev.db.dto;

import java.util.List;

import org.codehaus.jackson.JsonNode;

/**
 * Created by ainurminibaev on 13.05.16.
 */
public class TableDataResponse {

    private Integer recordsTotal;

    private Integer recordsFiltered;

    private List<TableViewRow> data;

    public Integer getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Integer recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Integer getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Integer recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<TableViewRow> getData() {
        return data;
    }

    public void setData(List<TableViewRow> data) {
        this.data = data;
    }
}
