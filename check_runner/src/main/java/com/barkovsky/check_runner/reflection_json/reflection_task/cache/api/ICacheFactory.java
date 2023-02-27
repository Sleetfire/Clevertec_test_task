package com.barkovsky.check_runner.reflection_json.reflection_task.cache.api;

import com.barkovsky.check_runner.reflection_json.reflection_task.entity.Student;

public interface ICacheFactory {

    /**
     * Getting cache realization with String items
     * @return cache with Integer key and String value
     */
    ICache<Integer, String> getStringCache();

    /**
     * Getting cache realization with Student items
     * @return cache with Long key and Student value
     */
    ICache<Long, Student> getStudentCache();

}
