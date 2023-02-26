package com.barkovsky.check_runner.reflection_task.dao.api;

import com.barkovsky.check_runner.reflection_task.entity.Student;

import java.util.Optional;

public interface IStudentDAO {

    /**
     * Getting student from the storage
     * @param id student's id
     * @return student in Optional wrapper
     */
    Optional<Student> get(long id);

    /**
     * Adding student to the storage
     * @param student student's entity
     */
    void add(Student student);

    /**
     * Deleting student from the storage
     * @param id student's id
     */
    void delete(long id);

    /**
     * Updating student in storage
     * @param id student's id
     * @param updatedStudent student's entity with updated fields
     */
    void update(long id, Student updatedStudent);

}
