package com.injob.back.enums;

public enum StatusEnum {
    ACCEPTED("Accepté"),
    PENDING("En attente"),
    REJECTED("Rejeté");

    private final String value;

    StatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
