package ru.ainurminibaev.db.dto;

/**
 * Created by ainurminibaev on 15.05.16.
 */
public class HeaderView {

    private String val;

    private String str;

    public HeaderView(String val, String str) {
        this.val = val;
        this.str = str;
    }

    public HeaderView(String column) {
        this.val = column;
        this.str = column;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
