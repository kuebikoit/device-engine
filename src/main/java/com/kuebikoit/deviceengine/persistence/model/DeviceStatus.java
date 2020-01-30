package com.kuebikoit.deviceengine.persistence.model;

public enum DeviceStatus {
    VULNERABLE("vulnerable"),
    NON_VULNERABLE("non-vulnerable"),
    NON_EXISTENT("tes");

    private String value;

    DeviceStatus(String s) {
        this.value = s;
    }

    public String getValue() {
        return value;
    }
}
