package com.barkovsky.check_runner.reflection_task.dao;

import com.barkovsky.check_runner.reflection_task.dao.api.IStudentDAO;
import com.barkovsky.check_runner.reflection_task.entity.Student;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class StudentDAO implements IStudentDAO {

    private final Map<Integer, Student> content = new HashMap<>();

    @Override
    public Optional<Student> get(int id) {
        return Optional.ofNullable(content.get(id));
    }

    @Override
    public void add(Student student) {
        this.content.put(student.getId(), student);
    }

    @Override
    public void delete(int id) {
        this.content.remove(id);
    }

    @Override
    public void update(int id, Student updatedStudent) {
        this.content.replace(id, updatedStudent);
    }
}
