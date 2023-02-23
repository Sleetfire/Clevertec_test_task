package com.barkovsky.check_runner.reflection_task.service;

import com.barkovsky.check_runner.exception.EssenceNotFoundException;
import com.barkovsky.check_runner.reflection_task.dao.api.IStudentDAO;
import com.barkovsky.check_runner.reflection_task.entity.Student;
import com.barkovsky.check_runner.reflection_task.service.api.IStudentService;

import java.util.Optional;

public class StudentService implements IStudentService {

    private final IStudentDAO studentDAO;

    public StudentService(IStudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public Student get(int id) {
        Optional<Student> optionalStudent = this.studentDAO.get(id);
        if (optionalStudent.isEmpty()) {
            throw new EssenceNotFoundException("Essence with id " + id + " doesn't exist");
        }
        return optionalStudent.get();
    }

    @Override
    public void add(Student student) {
        this.studentDAO.add(student);
    }

    @Override
    public void delete(int id) {
        this.studentDAO.delete(id);
    }

    @Override
    public void update(int id, Student updatedStudent) {
        this.studentDAO.update(id, updatedStudent);
    }
}
