package com.barkovsky.check_runner.reflection_task.service.api;

import com.barkovsky.check_runner.reflection_task.entity.Student;

public interface IStudentService {

    Student get(int id);

    void add(Student student);

    void delete(int id);

    void update(int id, Student updatedStudent);

}
