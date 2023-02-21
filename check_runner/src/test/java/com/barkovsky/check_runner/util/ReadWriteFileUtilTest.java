package com.barkovsky.check_runner.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ReadWriteFileUtilTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Reading to the file and writing")
    void checkWriteStringInFile() {
        UUID testUUID = UUID.fromString("eb21d3b4-b21e-11ed-afa1-0242ac120002");
        String testString = "Hello world!";
        try(MockedStatic<UUID> mockedStatic = Mockito.mockStatic(UUID.class)) {
            mockedStatic.when(UUID::randomUUID).thenReturn(testUUID);

            ReadWriteFileUtil.writeStringInFile(testString, ".txt");
            String stringFromDb = ReadWriteFileUtil.readStringFromFile(testUUID + ".txt");

            Assertions.assertThat(stringFromDb).contains(testString);
        }
    }

}