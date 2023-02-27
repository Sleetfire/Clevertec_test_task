package com.barkovsky.check_runner.reflection_json.reflection_task.dao;

import com.barkovsky.check_runner.reflection_json.reflection_task.dao.api.IStudentDAO;
import com.barkovsky.check_runner.reflection_json.reflection_task.entity.Student;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class StudentDAO implements IStudentDAO {

    private final Map<Long, Student> content = new HashMap<>();

    @Override
    public Optional<Student> get(long id) {
        return Optional.ofNullable(content.get(id));
    }

    @Override
    public void add(Student student) {
        this.content.put(student.getId(), student);
    }

    @Override
    public void delete(long id) {
        this.content.remove(id);
    }

    @Override
    public void update(long id, Student updatedStudent) {
        this.content.replace(id, updatedStudent);
    }
}
