package com.mangalog.ryuuga.a40kdiceapp.enums.system;

public enum Value {
    BLANK("", 0);

    private String text;
    private Integer integer;

    Value(String text, Integer integer) {
        this.text = text;
        this.integer = integer;
    }

    public String getString() {
        return text;
    }

    public Integer getInteger() {
        return integer;
    }
}
