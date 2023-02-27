package com.barkovsky.check_runner.reflection_json.reflection_task.cache.api;

import com.barkovsky.check_runner.reflection_json.reflection_task.cache.LFUCache;
import com.barkovsky.check_runner.reflection_json.reflection_task.cache.LRUCache;
import com.barkovsky.check_runner.reflection_json.reflection_task.entity.Student;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class ChoiceCacheFactory implements ICacheFactory {

    private final ICache cache;

    public ChoiceCacheFactory() {
        String algorithm = this.getAlgorithm();
        int capacity = this.getCapacity();
        switch (algorithm) {
            case "LFU":
                this.cache = new LFUCache<>(capacity);
                break;
            case "LRU":
            default:
                this.cache = new LRUCache<>(capacity);
        }
    }

    @Override
    public ICache<Integer, String> getStringCache() {
        return this.cache;
    }

    @Override
    public ICache<Long, Student> getStudentCache() {
        return this.cache;
    }

    private int getCapacity() {
        PropertiesConfiguration configuration = new PropertiesConfiguration();
        try {
            configuration.load("application.yml");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        return configuration.getInt("cash-capacity");
    }

    private String getAlgorithm() {
        PropertiesConfiguration configuration = new PropertiesConfiguration();
        try {
            configuration.load("application.yml");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        return configuration.getString("cash-algorithm");
    }
}
