package com.barkovsky.check_runner.reflection_task.cache.api;

public interface ICacheFactory {

    ICache<Integer, String> getStringCache();

}
