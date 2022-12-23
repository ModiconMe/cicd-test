package com.example.springbootfullstack.student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @Test
    void itShouldFindByEmail_whenStudentExists() {
        // given
        String email = "jamila@gmail.com";
        Student student = Student.builder()
                .name("Jamila")
                .email(email)
                .gender(Gender.FEMALE)
                .build();

        // when
        underTest.save(student);

        // then
        Optional<Student> expected = underTest.findByEmail(email);
        assertThat(expected).isPresent();
    }

    @Test
    void itShouldFindByEmail_whenStudentIsNotExists() {
        // given
        String email = "jamila@gmail.com";

        // when
        // then
        Optional<Student> expected = underTest.findByEmail(email);
        assertThat(expected).isEmpty();
    }
}