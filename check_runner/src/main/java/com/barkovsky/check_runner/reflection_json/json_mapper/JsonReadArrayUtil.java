package com.barkovsky.check_runner.reflection_json.json_mapper;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class JsonReadArrayUtil {

    private JsonReadArrayUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static <T> void setByteArrayField(JsonObject jsonObject, Field field, T result) throws IllegalAccessException {
        String fieldName = field.getName();
        JsonArray jsonArray = jsonObject.getJsonArray(fieldName);
        List<Byte> byteList = new ArrayList<>();
        byte[] array = new byte[jsonArray.size()];
        for (JsonValue jsonValue : jsonArray) {
            byteList.add(Byte.valueOf(jsonValue.toString()));
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = byteList.get(i);
        }

        field.set(result, array);
    }

    public static <T> void setShortArrayField(JsonObject jsonObject, Field field, T result) throws IllegalAccessException {
        String fieldName = field.getName();
        JsonArray jsonArray = jsonObject.getJsonArray(fieldName);
        List<Short> shortList = new ArrayList<>();
        short[] array = new short[jsonArray.size()];
        for (JsonValue jsonValue : jsonArray) {
            shortList.add(Short.valueOf(jsonValue.toString()));
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = shortList.get(i);
        }

        field.set(result, array);
    }

    public static <T> void setIntArrayField(JsonObject jsonObject, Field field, T result) throws IllegalAccessException {
        String fieldName = field.getName();
        JsonArray jsonArray = jsonObject.getJsonArray(fieldName);
        List<Integer> integerList = new ArrayList<>();
        int[] array = new int[jsonArray.size()];
        for (JsonValue jsonValue : jsonArray) {
            integerList.add(Integer.valueOf(jsonValue.toString()));
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = integerList.get(i);
        }

        field.set(result, array);
    }

    public static <T> void setLongArrayField(JsonObject jsonObject, Field field, T result) throws IllegalAccessException {
        String fieldName = field.getName();
        JsonArray jsonArray = jsonObject.getJsonArray(fieldName);
        List<Long> longList = new ArrayList<>();
        long[] array = new long[jsonArray.size()];
        for (JsonValue jsonValue : jsonArray) {
            longList.add(Long.valueOf(jsonValue.toString()));
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = longList.get(i);
        }

        field.set(result, array);
    }

    public static <T> void setCharArrayField(JsonObject jsonObject, Field field, T result) throws IllegalAccessException {
        String fieldName = field.getName();
        JsonArray jsonArray = jsonObject.getJsonArray(fieldName);
        List<Character> characterList = new ArrayList<>();
        char[] array = new char[jsonArray.size()];
        for (JsonValue jsonValue : jsonArray) {
            characterList.add(jsonValue.toString().charAt(0));
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = characterList.get(i);
        }

        field.set(result, array);
    }

    public static <T> void setFloatArrayField(JsonObject jsonObject, Field field, T result) throws IllegalAccessException {
        String fieldName = field.getName();
        JsonArray jsonArray = jsonObject.getJsonArray(fieldName);
        List<Float> floatList = new ArrayList<>();
        float[] array = new float[jsonArray.size()];
        for (JsonValue jsonValue : jsonArray) {
            floatList.add(Float.valueOf(jsonValue.toString()));
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = floatList.get(i);
        }

        field.set(result, array);
    }

    public static <T> void setDoubleArrayField(JsonObject jsonObject, Field field, T result) throws IllegalAccessException {
        String fieldName = field.getName();
        JsonArray jsonArray = jsonObject.getJsonArray(fieldName);
        List<Double> doubleList = new ArrayList<>();
        double[] array = new double[jsonArray.size()];
        for (JsonValue jsonValue : jsonArray) {
            doubleList.add(Double.valueOf(jsonValue.toString()));
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = doubleList.get(i);
        }

        field.set(result, array);
    }

    public static <T> void setBooleanArrayField(JsonObject jsonObject, Field field, T result) throws IllegalAccessException {
        String fieldName = field.getName();
        JsonArray jsonArray = jsonObject.getJsonArray(fieldName);
        List<Boolean> booleanList = new ArrayList<>();
        boolean[] array = new boolean[jsonArray.size()];
        for (JsonValue jsonValue : jsonArray) {
            booleanList.add(Boolean.valueOf(jsonValue.toString()));
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = booleanList.get(i);
        }

        field.set(result, array);
    }

    public static <T> void setStringArrayField(JsonObject jsonObject, Field field, T result) throws IllegalAccessException {
        String fieldName = field.getName();
        JsonArray jsonArray = jsonObject.getJsonArray(fieldName);
        List<String> stringList = new ArrayList<>();
        String[] array = new String[jsonArray.size()];
        for (JsonValue jsonValue : jsonArray) {
            stringList.add(jsonValue.toString());
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = stringList.get(i);
        }

        field.set(result, array);
    }

}
