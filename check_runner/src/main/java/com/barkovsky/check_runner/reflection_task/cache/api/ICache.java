package com.barkovsky.check_runner.reflection_task.cache.api;

import java.util.Optional;

public interface ICache<K, V> {

    void add(K key, V value);

    Optional<V> get(K key);

    void delete(K key);

    int getSize();

    int getCapacity();

    boolean isEmpty();

    void clear();

}
