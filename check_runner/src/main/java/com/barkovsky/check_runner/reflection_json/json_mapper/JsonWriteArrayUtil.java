package com.barkovsky.check_runner.reflection_json.json_mapper;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.lang.reflect.Field;

public class JsonWriteArrayUtil {

    private JsonWriteArrayUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static <T> void getByteArrayField(JsonObjectBuilder objectBuilder, Field field, T content) throws IllegalAccessException {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (byte b : (byte[]) field.get(content)) {
            arrayBuilder.add(b);
        }
        objectBuilder.add(field.getName(), arrayBuilder);
    }

    public static <T> void getShortArrayField(JsonObjectBuilder objectBuilder, Field field, T content) throws IllegalAccessException {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (short s : (short[]) field.get(content)) {
            arrayBuilder.add(s);
        }
        objectBuilder.add(field.getName(), arrayBuilder);
    }

    public static <T> void getIntArrayField(JsonObjectBuilder objectBuilder, Field field, T content) throws IllegalAccessException {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (int i : (int[]) field.get(content)) {
            arrayBuilder.add(i);
        }
        objectBuilder.add(field.getName(), arrayBuilder);
    }

    public static <T> void getLongArrayField(JsonObjectBuilder objectBuilder, Field field, T content) throws IllegalAccessException {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (long l : (long[]) field.get(content)) {
            arrayBuilder.add(l);
        }
        objectBuilder.add(field.getName(), arrayBuilder);
    }

    public static <T> void getCharArrayField(JsonObjectBuilder objectBuilder, Field field, T content) throws IllegalAccessException {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (char c : (char[]) field.get(content)) {
            arrayBuilder.add(c);
        }
        objectBuilder.add(field.getName(), arrayBuilder);
    }

    public static <T> void getFloatArrayField(JsonObjectBuilder objectBuilder, Field field, T content) throws IllegalAccessException {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (float f : (float[]) field.get(content)) {
            arrayBuilder.add(f);
        }
        objectBuilder.add(field.getName(), arrayBuilder);
    }

    public static <T> void getDoubleArrayField(JsonObjectBuilder objectBuilder, Field field, T content) throws IllegalAccessException {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (double d : (double[]) field.get(content)) {
            arrayBuilder.add(d);
        }
        objectBuilder.add(field.getName(), arrayBuilder);
    }

    public static <T> void getBooleanArrayField(JsonObjectBuilder objectBuilder, Field field, T content) throws IllegalAccessException {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (boolean b : (boolean[]) field.get(content)) {
            arrayBuilder.add(b);
        }
        objectBuilder.add(field.getName(), arrayBuilder);
    }

    public static <T> void getStringArrayField(JsonObjectBuilder objectBuilder, Field field, T content) throws IllegalAccessException {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (String s : (String[]) field.get(content)) {
            arrayBuilder.add(s);
        }
        objectBuilder.add(field.getName(), arrayBuilder);
    }

}
