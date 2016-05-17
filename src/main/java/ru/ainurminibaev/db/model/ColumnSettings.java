package ru.ainurminibaev.db.model;

import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by ainurminibaev on 15.05.16.
 */
public class ColumnSettings {
    private String name;

    private String printable;

    @JsonProperty("is_str_enable")
    @Field("is_str_enable")
    private Boolean isStrEnable;

    private Boolean visible;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public String getPrintable() {
        return printable;
    }

    public void setPrintable(String printable) {
        this.printable = printable;
    }

    public Boolean getStrEnable() {
        return isStrEnable;
    }

    public void setStrEnable(Boolean strEnable) {
        isStrEnable = strEnable;
    }
}
