package com.barkovsky.check_runner.reflection_task.cache.api;

import java.util.Optional;

public interface ICache<K, V> {

    /**
     * Adding item to the cache
     * @param key cache collection's key
     * @param value collection's item
     */
    void add(K key, V value);

    /**
     * Getting item by key
     * @param key item's id in collection
     * @return item in Optional wrapper
     */
    Optional<V> get(K key);

    /**
     * Deleting item from collection by key
     * @param key item's id in collection
     */
    void delete(K key);

    /**
     * Getting collection's size (item's actual count)
     * @return actual item's count
     */
    int getSize();

    /**
     * Getting collection's capacity (collection's max size)
     * @return collection's max size
     */
    int getCapacity();

    /**
     * Checking is collection empty
     * @return true if collection doesn't contain items, false if collection contain items
     */
    boolean isEmpty();

    /**
     * Deleting all collection's items
     */
    void clear();

}
