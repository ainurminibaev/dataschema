package ru.ainurminibaev.db.dto;

import java.util.Objects;

/**
 * Created by ainurminibaev on 15.05.16.
 */
public enum SortEnum {
    ASC(1, "ASC"), DESC(-1, "DESC");
    private Integer order;
    private String str;

    SortEnum(Integer order, String str) {
        this.order = order;
        this.str = str;
    }

    public static SortEnum get(Integer order) {
        if (order == null) {
            return null;
        }
        for (SortEnum sortEnum : SortEnum.values()) {
            if (Objects.equals(sortEnum.order, order)) {
                return sortEnum;
            }
        }
        return null;
    }

    public String getStr() {
        return str;
    }
}
