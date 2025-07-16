package com.injob.back.enums;

public enum InterviewStatusEnum {
    PLANNED("planifie"),
    DONE("realiser"),
    CANCELED("annuler");
    private final String value;

    InterviewStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
