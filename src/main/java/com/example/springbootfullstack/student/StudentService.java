package com.example.springbootfullstack.student;

import com.example.springbootfullstack.student.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        if (studentRepository.findByEmail(student.getEmail()).isPresent()) {
            ApiException.build(HttpStatus.BAD_REQUEST,
                    "student with email \"%s\" already exists", student.getEmail());
        }

        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        if (studentRepository.findById(id).isEmpty()) {
            ApiException.build(HttpStatus.NOT_FOUND,
                    "student with id \"%d\" is not found", id);
        }
        studentRepository.deleteById(id);
    }

}
