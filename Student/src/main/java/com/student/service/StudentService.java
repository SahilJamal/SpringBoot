package com.student.service;

import com.student.Dto.StudentDto;

import java.util.List;

public interface StudentService {
    List<StudentDto> getAllStudent();
    StudentDto getById(long id);
    StudentDto saveStudent(StudentDto studentDto);
    StudentDto updateStudent(StudentDto studentDto);
    List<StudentDto> deleteStudentById(long id);
}
