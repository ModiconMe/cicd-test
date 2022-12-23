package com.example.springbootfullstack.integration;

import com.example.springbootfullstack.student.Gender;
import com.example.springbootfullstack.student.Student;
import com.example.springbootfullstack.student.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-it.properties")
public class StudentIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StudentRepository studentRepository;

    private final Faker faker = new Faker();

    @Test
    void itShouldAddStudent() throws Exception {
        // given
        String name = String.format("%s %s", faker.name().firstName(), faker.name().lastName());
        String email = String.format("%s@gmail.com", StringUtils.replaceWhitespaceCharacters(name, ""));
        Student student = Student.builder()
                .name(name)
                .email(email)
                .gender(Gender.FEMALE)
                .build();
        // when
        ResultActions resultActions = mockMvc.perform(post("/api/v1/students").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)));
        // then
        resultActions.andExpect(status().isOk());
        studentRepository.findByEmail(email);
    }

    @Test
    void itShouldDeleteStudent() throws Exception {
        // given
        String name = String.format("%s %s", faker.name().firstName(), faker.name().lastName());
        String email = String.format("%s@gmail.com", StringUtils.replaceWhitespaceCharacters(name, ""));
        Student student = Student.builder()
                .name(name)
                .email(email)
                .gender(Gender.FEMALE)
                .build();
        // when
        mockMvc.perform(post("/api/v1/students").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student))).andExpect(status().isOk());
        Optional<Student> optionalStudent = studentRepository.findByEmail(email);
        assertThat(optionalStudent).isPresent();
        Long id = optionalStudent.get().getId();

        mockMvc.perform(delete("/api/v1/students/" + id).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student))).andExpect(status().isOk());

        // then
        Optional<Student> expected = studentRepository.findByEmail(email);
        assertThat(expected).isEmpty();
    }

}
