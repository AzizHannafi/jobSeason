package com.injob.back.enums;
public enum JobOfferTypeEnum {
    STAGE("Stage"),
    EMPLOI("Emploi");

    private final String value;

    JobOfferTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
