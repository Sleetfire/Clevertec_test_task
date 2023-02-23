package com.barkovsky.check_runner.reflection_task.dao.api;

import com.barkovsky.check_runner.reflection_task.entity.Student;

import java.util.Optional;

public interface IStudentDAO {

    Optional<Student> get(int id);

    void add(Student student);

    void delete(int id);

    void update(int id, Student updatedStudent);

}
