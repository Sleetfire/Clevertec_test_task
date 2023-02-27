package com.barkovsky.check_runner.reflection_json.json_mapper;

import com.barkovsky.check_runner.reflection_json.json_mapper.JsonMapper;
import com.barkovsky.check_runner.reflection_json.json_mapper.dto.TestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class JsonMapperTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final JsonMapper jsonMapper = new JsonMapper();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Checking writing object to string with json and mapping json string to the object")
    void checkJsonWriteAndRead() {
        TestDto testDto = new TestDto(1, 2.0, "3", new short[]{1, 2, 3, 4, 5});
        try {
            String resultJsonMapper = jsonMapper.write(testDto, TestDto.class);
            TestDto testDtoRead = jsonMapper.read(resultJsonMapper, TestDto.class);
            Assertions.assertThat(testDtoRead)
                    .hasFieldOrPropertyWithValue("intField", 1)
                    .hasFieldOrPropertyWithValue("doubleField", 2.0)
                    .hasFieldOrPropertyWithValue("stringField", "3")
                    .hasFieldOrPropertyWithValue("shorts", new short[]{1, 2, 3, 4, 5});
        } catch (IOException | ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

}