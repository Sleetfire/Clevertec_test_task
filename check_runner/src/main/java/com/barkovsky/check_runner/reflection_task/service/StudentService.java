package com.barkovsky.check_runner.reflection_task.service;

import com.barkovsky.check_runner.exception.EssenceNotFoundException;
import com.barkovsky.check_runner.reflection_task.dao.api.IStudentDAO;
import com.barkovsky.check_runner.reflection_task.dao.proxy.StudentDAOProxy;
import com.barkovsky.check_runner.reflection_task.entity.Student;
import com.barkovsky.check_runner.reflection_task.service.api.IStudentService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Optional;

public class StudentService implements IStudentService {

    private final IStudentDAO proxy;

    public StudentService(IStudentDAO studentDAO) {
        InvocationHandler handler = new StudentDAOProxy(studentDAO);
        Class[] classes = new Class[]{IStudentDAO.class};
        proxy = (IStudentDAO) Proxy.newProxyInstance(IStudentDAO.class.getClassLoader(), classes, handler);

    }

    @Override
    public Student get(long id) {
        Optional<Student> optionalStudent = this.proxy.get(id);
        if (optionalStudent.isEmpty()) {
            throw new EssenceNotFoundException("Essence with id " + id + " doesn't exist");
        }
        return optionalStudent.get();
    }

    @Override
    public void add(Student student) {
        this.proxy.add(student);
    }

    @Override
    public void delete(long id) {
        this.proxy.delete(id);
    }

    @Override
    public void update(long id, Student updatedStudent) {
        this.proxy.update(id, updatedStudent);
    }
}
