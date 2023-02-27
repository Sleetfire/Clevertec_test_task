package com.barkovsky.check_runner.reflection_json.json_mapper;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class JsonMapper {

    public <T> String write(T content, Class<T> clazz) throws IllegalAccessException, IOException {
        List<Field> fieldList = this.getFieldList(clazz);

        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        for (Field field : fieldList) {
            field.setAccessible(true);
            fillModel(objectBuilder, field, content);
        }

        String result;
        JsonObject jsonObject = objectBuilder.build();

        try (Writer writer = new StringWriter()) {
            Json.createWriter(writer).write(jsonObject);
            result = writer.toString();
        }
        return result;
    }

    public <T> T read(String content, Class<T> clazz) throws ReflectiveOperationException {
        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject jsonObject = reader.readObject();

        List<Field> fieldList = this.getFieldList(clazz);

        T result = clazz.getDeclaredConstructor().newInstance();
        for (Field field : fieldList) {
            field.setAccessible(true);
            this.fillObject(jsonObject, field, result);
        }
        return result;
    }

    private <T> void fillObject(JsonObject jsonObject, Field field, T result) throws IllegalAccessException {
        Class<?> type = field.getType();
        String fieldType = type.getSimpleName();
        String fieldName = field.getName();

        if (type.isPrimitive()) {
            this.readPrimitive(jsonObject, field, result);
            return;
        }

        if (type.isArray()) {
            this.readArray(jsonObject, field, result);
            return;
        }

        if (Objects.equals(fieldType, "String")) {
            String value = jsonObject.getString(fieldName);
            field.set(result, value);
        }
    }

    private <T> void fillModel(JsonObjectBuilder objectBuilder, Field field, T content) throws IllegalAccessException {
        Class<?> type = field.getType();
        String fieldType = type.getSimpleName();
        String fieldName = field.getName();
        if (type.isPrimitive()) {
            this.writePrimitive(objectBuilder, field, content);
            return;
        }

        if (type.isArray()) {
            this.writeArray(objectBuilder, field, content);
            return;
        }

        if (Objects.equals(fieldType, "String")) {
            objectBuilder.add(fieldName, (String) field.get(content));
        }
    }

    private <T> void writePrimitive(JsonObjectBuilder objectBuilder, Field field, T content) throws IllegalAccessException {
        Class<?> type = field.getType();
        String fieldType = type.getSimpleName();
        String fieldName = field.getName();
        if (fieldType.contains("byte") || fieldType.contains("short") || fieldType.contains("int") || fieldType.contains("long")) {
            objectBuilder.add(fieldName, field.getLong(content));
            return;
        }
        if (fieldType.contains("char")) {
            objectBuilder.add(fieldName, field.getChar(content));
            return;
        }
        if (fieldType.contains("float") || fieldType.contains("double")) {
            objectBuilder.add(fieldName, field.getDouble(content));
            return;
        }
        if (fieldType.contains("boolean")) {
            objectBuilder.add(fieldName, field.getBoolean(content));
        }
    }

    private <T> void readPrimitive(JsonObject jsonObject, Field field, T result) throws IllegalAccessException {
        Class<?> type = field.getType();
        String fieldType = type.getSimpleName();
        if (fieldType.contains("byte")) {
            JsonReadPrimitiveUtil.setByteField(jsonObject, field, result);
            return;
        }
        if (fieldType.contains("short")) {
            JsonReadPrimitiveUtil.setShortField(jsonObject, field, result);
            return;
        }
        if (fieldType.contains("int")) {
            JsonReadPrimitiveUtil.setIntField(jsonObject, field, result);
            return;
        }
        if (fieldType.contains("long")) {
            JsonReadPrimitiveUtil.setLongField(jsonObject, field, result);
            return;
        }

        if (fieldType.contains("char")) {
            JsonReadPrimitiveUtil.setCharField(jsonObject, field, result);
            return;
        }
        if (fieldType.contains("float")) {
            JsonReadPrimitiveUtil.setFloatField(jsonObject, field, result);
            return;
        }
        if (fieldType.contains("double")) {
            JsonReadPrimitiveUtil.setDoubleField(jsonObject, field, result);
            return;
        }
        if (fieldType.contains("boolean")) {
            JsonReadPrimitiveUtil.setBooleanField(jsonObject, field, result);
        }
    }

    private <T> void writeArray(JsonObjectBuilder objectBuilder, Field field, T content) throws IllegalAccessException {
        Class<?> type = field.getType();
        String fieldType = type.getSimpleName();
        if (fieldType.contains("byte[]")) {
            JsonWriteArrayUtil.getByteArrayField(objectBuilder, field, content);
            return;
        }
        if (fieldType.contains("short[]")) {
            JsonWriteArrayUtil.getShortArrayField(objectBuilder, field, content);
            return;
        }
        if (fieldType.contains("int[]")) {
            JsonWriteArrayUtil.getIntArrayField(objectBuilder, field, content);
            return;
        }
        if (fieldType.contains("long[]")) {
            JsonWriteArrayUtil.getLongArrayField(objectBuilder, field, content);
            return;
        }
        if (fieldType.contains("char[]")) {
            JsonWriteArrayUtil.getCharArrayField(objectBuilder, field, content);
            return;
        }
        if (fieldType.contains("float[]")) {
            JsonWriteArrayUtil.getFloatArrayField(objectBuilder, field, content);
            return;
        }
        if (fieldType.contains("double[]")) {
            JsonWriteArrayUtil.getDoubleArrayField(objectBuilder, field, content);
            return;
        }
        if (fieldType.contains("boolean[]")) {
            JsonWriteArrayUtil.getBooleanArrayField(objectBuilder, field, content);
            return;
        }
        if (fieldType.contains("String[]")) {
            JsonWriteArrayUtil.getStringArrayField(objectBuilder, field, content);
        }
    }

    private <T> void readArray(JsonObject jsonObject, Field field, T result) throws IllegalAccessException {
        Class<?> type = field.getType();
        String fieldType = type.getSimpleName();

        if (fieldType.contains("byte[]")) {
            JsonReadArrayUtil.setByteArrayField(jsonObject, field, result);
            return;
        }
        if (fieldType.contains("short[]")) {
            JsonReadArrayUtil.setShortArrayField(jsonObject, field, result);
            return;
        }
        if (fieldType.contains("int[]")) {
            JsonReadArrayUtil.setIntArrayField(jsonObject, field, result);
            return;
        }
        if (fieldType.contains("long[]")) {
            JsonReadArrayUtil.setLongArrayField(jsonObject, field, result);
            return;
        }
        if (fieldType.contains("char[]")) {
            JsonReadArrayUtil.setCharArrayField(jsonObject, field, result);
            return;
        }
        if (fieldType.contains("float[]")) {
            JsonReadArrayUtil.setFloatArrayField(jsonObject, field, result);
            return;
        }
        if (fieldType.contains("double[]")) {
            JsonReadArrayUtil.setDoubleArrayField(jsonObject, field, result);
            return;
        }
        if (fieldType.contains("boolean[]")) {
            JsonReadArrayUtil.setBooleanArrayField(jsonObject, field, result);
            return;
        }
        if (fieldType.contains("String[]")) {
            JsonReadArrayUtil.setStringArrayField(jsonObject, field, result);
        }
    }

    private <T> List<Field> getFieldList(Class<T> clazz) {
        Field[] declaredFields = clazz.getDeclaredFields();
        Field[] fields = clazz.getFields();
        return Stream.concat(Arrays.stream(declaredFields), Arrays.stream(fields))
                .distinct()
                .toList();
    }

}
