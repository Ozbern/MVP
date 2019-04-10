package com.example.mvp.Model;

public class DataItem {
    private String field;
    private String description;

    public DataItem(String field, String description) {
        this.field = field;
        this.description = description;
    }

    public String getField() {
        return field;
    }

    public String getDescription() {
        return description;
    }
}
