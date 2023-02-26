package com.barkovsky.check_runner.json_mapper.dto;

import java.util.Arrays;

public class TestDto {
    private int intField;
    private double doubleField;
    private String stringField;
    private short[] shorts;

    public TestDto(int intField, double doubleField, String stringField, short[] shorts) {
        this.intField = intField;
        this.doubleField = doubleField;
        this.stringField = stringField;
        this.shorts = shorts;
    }

    public TestDto() {
    }

    @Override
    public String toString() {
        return "TestDto{" +
                "intField=" + intField +
                ", doubleField=" + doubleField +
                ", stringField='" + stringField + '\'' +
                ", shorts=" + Arrays.toString(shorts) +
                '}';
    }
}
