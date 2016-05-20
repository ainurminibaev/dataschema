package ru.ainurminibaev.db.dto;

/**
 * Created by ainurminibaev on 11.05.16.
 */
public class TableViewCol {

    private String strVal;

    private String val;

    public TableViewCol(String val, String strVal) {
        this.val = val;
        this.strVal = strVal;
    }

    public TableViewCol() {
    }

    public TableViewCol(String strVal) {
        this.strVal = strVal;
    }

    public String getStrVal() {
        return strVal;
    }

    public void setStrVal(String strVal) {
        this.strVal = strVal;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
