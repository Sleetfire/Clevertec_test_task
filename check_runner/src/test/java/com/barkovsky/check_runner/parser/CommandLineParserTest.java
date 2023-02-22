package com.barkovsky.check_runner.parser;

import com.barkovsky.check_runner.exception.CommandLineArgumentException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CommandLineParserTest {

    private CommandLineParser commandLineParser;

    @BeforeEach
    void setUp() {
        this.commandLineParser = new CommandLineParser();
    }

    @AfterEach
    void tearDown() {
    }

    @ParameterizedTest(name = "{index} - checked arg: {0}")
    @CsvSource(value = {
            "42-5555, 42",
            "0-1, 0",
            "11111-17, 11111"
    })
    @DisplayName("Getting id from String arg")
    void checkGetIdShouldReturnInt(String arg, int id) {
        int checkedId = this.commandLineParser.getId(arg);

        Assertions.assertThat(checkedId).isEqualTo(id);
    }

    @ParameterizedTest(name = "{index} - checked arg: {0}")
    @ValueSource(strings = {"aa-0", "1a-1", "1,5-ff"})
    @DisplayName("Getting id from String arg should throw CommandLineArgumentException")
    void checkGetIdShouldThrowCommandLineArgumentException(String arg) {
        Assertions.assertThatThrownBy(() -> this.commandLineParser.getId(arg))
                .isInstanceOf(CommandLineArgumentException.class);
    }

    @ParameterizedTest(name = "{index} - checked arg: {0}")
    @CsvSource(value = {
            "42-5555, 5555",
            "0-1, 1",
            "aaa-17, 17"
    })
    @DisplayName("Getting count from String arg")
    void checkGetCountShouldReturnInt(String arg, int count) {
        int checkedCount = this.commandLineParser.getCount(arg);

        Assertions.assertThat(checkedCount).isEqualTo(count);
    }

    @ParameterizedTest(name = "{index} - checked arg: {0}")
    @ValueSource(strings = {"00-aa", "1-1a", "ff-1,5"})
    @DisplayName("Getting count from String arg should throw CommandLineArgumentException")
    void checkGetCountShouldThrowCommandLineArgumentException(String arg) {
        Assertions.assertThatThrownBy(() -> this.commandLineParser.getCount(arg))
                .isInstanceOf(CommandLineArgumentException.class);
    }

    @ParameterizedTest(name = "{index} - checked arg: {0}")
    @CsvSource(value = {
            "card-1111, 1111",
            "card-1234, 1234",
            "card-1, 1"
    })
    @DisplayName("Getting card number from String arg")
    void checkGetDiscountCardNumberShouldReturnInt(String arg, int cardNumber) {
        int checkedCardNumber = this.commandLineParser.getDiscountCardNumber(arg);

        Assertions.assertThat(checkedCardNumber).isEqualTo(cardNumber);
    }

    @ParameterizedTest(name = "{index} - checked arg: {0}")
    @ValueSource(strings = {"card-aa", "card-1a", "card-1,5"})
    @DisplayName("Getting count from String arg should throw CommandLineArgumentException")
    void checkGetDiscountCardNumberShouldThrowCommandLineArgumentException(String arg) {
        Assertions.assertThatThrownBy(() -> this.commandLineParser.getDiscountCardNumber(arg))
                .isInstanceOf(CommandLineArgumentException.class);
    }

    @ParameterizedTest(name = "{index} - checked arg: {0}")
    @CsvSource(value = {
            "filename-test.txt, test.txt",
            "filename-test.json, test.json"
    })
    @DisplayName("Getting card number from String arg")
    void checkGetFileNameShouldReturnString(String arg, String fileName) {
        String checkedFileName = this.commandLineParser.getFileName(arg);

        Assertions.assertThat(checkedFileName).isEqualTo(fileName);
    }
}