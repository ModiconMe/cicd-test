package com.example.springbootfullstack.student;

import com.example.springbootfullstack.student.exception.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    private StudentService underTest;

    @BeforeEach
    void setUp() {
        underTest = new StudentService(studentRepository);
    }

    @Test
    void itShouldGetAllStudents() {
        // when
        underTest.getAllStudent();
        // then
        verify(studentRepository).findAll();
    }

    @Test
    void itShouldAddStudent_whenStudentEmailIsNotExists() {
        // given
        Student student = Student.builder()
                .name("Jamila")
                .email("jamila@gmail.com")
                .gender(Gender.FEMALE)
                .build();

        // when
        underTest.addStudent(student);

        // then
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(studentArgumentCaptor.capture());
        Student capturedStudent = studentArgumentCaptor.getValue();
        assertThat(capturedStudent).isEqualTo(student);
    }

    @Test
    void itShouldNotAddStudent_whenStudentEmailExists() {
        // given
        String email = "jamila@gmail.com";
        Student student = Student.builder()
                .name("Jamila")
                .email(email)
                .gender(Gender.FEMALE)
                .build();
        when(studentRepository.findByEmail(email)).thenReturn(Optional.of(student));

        // when
        // then
        assertThatThrownBy(() -> underTest.addStudent(student))
                .isInstanceOf(ApiException.class)
                .hasMessageContaining("student with email \"%s\" already exists", email);
    }
}