package com.barkovsky.check_runner.reflection_json.reflection_task.service;

import com.barkovsky.check_runner.exception.EssenceNotFoundException;
import com.barkovsky.check_runner.reflection_json.reflection_task.dao.StudentDAO;
import com.barkovsky.check_runner.reflection_json.reflection_task.dao.api.IStudentDAO;
import com.barkovsky.check_runner.reflection_json.reflection_task.entity.Student;
import com.barkovsky.check_runner.reflection_json.reflection_task.service.StudentService;
import com.barkovsky.check_runner.reflection_json.reflection_task.service.api.IStudentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class StudentServiceTest {

    private IStudentService studentService;

    @BeforeEach
    void setUp() {
        IStudentDAO studentDAO = new StudentDAO();
        this.studentService = new StudentService(studentDAO);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Checking getting student from empty cache and storage")
    void checkGetForEmptyCacheShouldThrowEssenceNotFoundException() {
        Assertions.assertThatThrownBy(() -> this.studentService.get(1))
                .isInstanceOf(EssenceNotFoundException.class);
    }

    @ParameterizedTest(name = "{index} - checked arg: {0}")
    @MethodSource("getStudent")
    @DisplayName("Adding student to the cache and storage")
    void checkAddShouldReturnStudent(Student student) {
        this.studentService.add(student);
        Student studentFromStorage = this.studentService.get(student.getId());
        Assertions.assertThat(studentFromStorage).isEqualTo(student);
    }

    @ParameterizedTest(name = "{index} - checked arg: {0}")
    @MethodSource("getStudent")
    @DisplayName("Deleting student from cache and storage")
    void checkDeleteShouldThrowEssenceNotFoundException(Student student) {
        this.studentService.add(student);
        this.studentService.delete(student.getId());
        Assertions.assertThatThrownBy(() -> this.studentService.get(student.getId()))
                .isInstanceOf(EssenceNotFoundException.class);
    }

    @ParameterizedTest(name = "{index} - checked arg: {0}")
    @MethodSource("getStudent")
    @DisplayName("Updating student in cache and storage")
    void checkUpdateShouldReturnUpdatedStudent(Student student) {
        this.studentService.add(student);

        Student updated = Student.Builder.createBuilder()
                .setId(1L)
                .setFirstName("updated_first_name")
                .setLastName("updated_last_name")
                .setEmail("email@gmail.com")
                .setPhoneNumber("+375441234567")
                .setPassword(new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', '1'})
                .build();

        this.studentService.update(student.getId(), updated);

        Student studentFromStorage = this.studentService.get(student.getId());

        Assertions.assertThat(studentFromStorage).isEqualTo(updated);
    }

    static Stream<Student> getStudent() {
        Student student = Student.Builder.createBuilder()
                .setId(1L)
                .setFirstName("first_name")
                .setLastName("last_name")
                .setEmail("email@gmail.com")
                .setPhoneNumber("+375441234567")
                .setPassword(new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', '1'})
                .build();
        return Stream.of(student);
    }

}