package com.barkovsky.check_runner.reflection_json.reflection_task.dao.proxy;

import com.barkovsky.check_runner.reflection_json.reflection_task.cache.api.ChoiceCacheFactory;
import com.barkovsky.check_runner.reflection_json.reflection_task.cache.api.ICache;
import com.barkovsky.check_runner.reflection_json.reflection_task.cache.api.ICacheFactory;
import com.barkovsky.check_runner.reflection_json.reflection_task.dao.api.IStudentDAO;
import com.barkovsky.check_runner.reflection_json.reflection_task.entity.Student;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Optional;

public class StudentDAOProxy implements InvocationHandler {
    private final ICache<Long, Student> cache;
    private final IStudentDAO studentDAO;

    public StudentDAOProxy(IStudentDAO studentDAO) {
        ICacheFactory cacheFactory = new ChoiceCacheFactory();
        cache = cacheFactory.getStudentCache();
        this.studentDAO = studentDAO;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (Objects.equals("add", method.getName())) {
            this.add(method, args);
        }

        if (Objects.equals("get", method.getName())) {
            return get(method, args);
        }

        if (Objects.equals("delete", method.getName())) {
            delete(method, args);
        }

        if (Objects.equals("update", method.getName())) {
            update(method, args);
        }
        return new Object();
    }

    private void add(Method method, Object[] args) throws Throwable {
        Student student = (Student) args[0];
        method.invoke(studentDAO, args);
        cache.add(student.getId(), student);
    }

    private Optional<Student> get(Method method, Object[] args) throws Throwable {
        Long id = (Long) args[0];
        Optional<Student> optionalStudent = this.cache.get(id);
        if (optionalStudent.isPresent()) {
            return optionalStudent;
        } else {
            Optional<Student> optionalStudent1 = (Optional<Student>) method.invoke(studentDAO, args);
            optionalStudent1.ifPresent(student -> cache.add(id, student));
            return optionalStudent1;
        }
    }

    private void delete(Method method, Object[] args) throws Throwable {
        Long id = (Long) args[0];
        method.invoke(studentDAO, args);
        this.cache.delete(id);
    }

    private void update(Method method, Object[] args) throws Throwable {
        Long id = (Long) args[0];
        Student updatedStudent = (Student) args[1];
        method.invoke(studentDAO, args);
        this.cache.add(id, updatedStudent);
    }
}
