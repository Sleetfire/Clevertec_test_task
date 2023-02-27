package com.barkovsky.check_runner.reflection_json.reflection_task.service.api;

import com.barkovsky.check_runner.reflection_json.reflection_task.entity.Student;

public interface IStudentService {

    /**
     * Getting student by id
     * @param id student's id
     * @return student entity
     */
    Student get(long id);

    /**
     * Adding student
     * @param student student's entity
     */
    void add(Student student);

    /**
     * Deleting student by id
     * @param id student's id
     */
    void delete(long id);

    /**
     * Updating student
     * @param id student's id
     * @param updatedStudent student's entity with updated fields
     */
    void update(long id, Student updatedStudent);

}
