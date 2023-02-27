package com.barkovsky.check_runner.reflection_task.cache;

import com.barkovsky.check_runner.reflection_json.reflection_task.cache.LRUCache;
import com.barkovsky.check_runner.reflection_json.reflection_task.cache.api.ICache;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Optional;

class LRUCacheTest {

    private ICache<Integer, String> cache;

    @BeforeEach
    void setUp() {
        this.cache = new LRUCache<>(3);
    }

    @AfterEach
    void tearDown() {
    }

    @ParameterizedTest(name = "{index} - checked arg: {0}")
    @CsvSource(value = {
            "1, value1",
            "2, value-2",
            "3, value-3"
    })
    @DisplayName("Checking adding to the cache")
    void checkAddAndGetShouldReturnValue(int key, String value) {
        this.cache.add(key, value);
        Optional<String> optionalString = cache.get(key);
        Assertions.assertThat(optionalString).isEqualTo(Optional.of(value));
    }

    @Test
    @DisplayName("Checking getting value from empty cache")
    void checkGetShouldReturnOptionalEmpty() {
        Assertions.assertThat(this.cache.get(1))
                .isEqualTo(Optional.empty());
    }

    @Test
    @DisplayName("Checking value updating")
    void checkUpdateShouldReturnUpdatedValue() {
        String firstValue = "first";
        String nextValue = "next";

        this.cache.add(1, firstValue);
        this.cache.add(1, nextValue);

        Assertions.assertThat(this.cache.get(1))
                .isEqualTo(Optional.of(nextValue));
    }

    @Test
    @DisplayName("Checking deleting")
    void checkDeleteShouldReturnOptionalEmpty() {
        String value = "value";
        int key = 1;
        this.cache.add(key, value);
        this.cache.delete(key);
        Assertions.assertThat(this.cache.get(key))
                .isEqualTo(Optional.empty());
    }

    @Test
    @DisplayName("Checking getting size for empty cache")
    void checkGetSizeForEmptyCacheShouldReturn0() {
        int result = this.cache.getSize();
        Assertions.assertThat(result).isZero();
    }

    @Test
    @DisplayName("Checking getting size for empty cache")
    void checkGetSizeCacheShouldReturn1() {
        this.cache.add(1, "1");
        int result = this.cache.getSize();
        Assertions.assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("Checking getting cache's capacity")
    void checkGetCapacityShouldReturn3() {
        Assertions.assertThat(this.cache.getCapacity()).isEqualTo(3);
    }

    @Test
    @DisplayName("Checking getting is cache empty")
    void checkIsEmptyShouldReturnTrue() {
        Assertions.assertThat(this.cache.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Checking clearing cache")
    void clear() {
        this.cache.add(1, "1");
        this.cache.add(2, "2");
        this.cache.add(3, "3");
        this.cache.add(4, "4");
        this.cache.clear();
        Assertions.assertThat(this.cache.isEmpty()).isTrue();
    }
}