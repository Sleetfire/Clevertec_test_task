package com.barkovsky.check_runner.reflection_json.json_mapper;

import javax.json.JsonObject;
import java.lang.reflect.Field;

public class JsonReadPrimitiveUtil {

    private JsonReadPrimitiveUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static <T> void setByteField(JsonObject jsonObject, Field field, T result) throws IllegalAccessException {
        byte value = (byte) jsonObject.getJsonNumber(field.getName()).intValue();
        field.setByte(result, value);
    }

    public static <T> void setShortField(JsonObject jsonObject, Field field, T result) throws IllegalAccessException {
        short value = (short) jsonObject.getJsonNumber(field.getName()).intValue();
        field.setShort(result, value);
    }

    public static <T> void setIntField(JsonObject jsonObject, Field field, T result) throws IllegalAccessException {
        int value = jsonObject.getJsonNumber(field.getName()).intValue();
        field.setInt(result, value);
    }

    public static <T> void setLongField(JsonObject jsonObject, Field field, T result) throws IllegalAccessException {
        long value = jsonObject.getJsonNumber(field.getName()).longValue();
        field.setLong(result, value);
    }

    public static <T> void setCharField(JsonObject jsonObject, Field field, T result) throws IllegalAccessException {
        char value = jsonObject.getJsonString(field.getName()).getChars().charAt(0);
        field.setChar(result, value);
    }

    public static <T> void setFloatField(JsonObject jsonObject, Field field, T result) throws IllegalAccessException {
        float value = (float) jsonObject.getJsonNumber(field.getName()).doubleValue();
        field.setFloat(result, value);
    }

    public static <T> void setDoubleField(JsonObject jsonObject, Field field, T result) throws IllegalAccessException {
        double value = jsonObject.getJsonNumber(field.getName()).doubleValue();
        field.setDouble(result, value);
    }

    public static <T> void setBooleanField(JsonObject jsonObject, Field field, T result) throws IllegalAccessException {
        boolean value = jsonObject.getBoolean(field.getName());
        field.setBoolean(result, value);
    }

}
