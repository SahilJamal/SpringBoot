package com.student.service;

import com.student.Dto.StudentDto;
import com.student.entity.Student;
import com.student.mapper.Mapper;
import com.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService, UserDetailsService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<StudentDto> getAllStudent() {
        return  Mapper.studentEntityToStudentDto(studentRepository.findAll());
    }

    @Override
    public StudentDto getById(long id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.map(Mapper::studentEntityToStudentDto).orElse(null);
    }

    @Override
    public StudentDto saveStudent(StudentDto studentDto) {
        studentRepository.save(Objects.requireNonNull(Mapper.studentDtoToStudentEntity(studentDto)));
        return Mapper.studentEntityToStudentDto(studentRepository.findByName(studentDto.getName()));
    }

    @Override
    public StudentDto updateStudent(StudentDto studentDto) {
        studentRepository.save(Objects.requireNonNull(Mapper.studentDtoToStudentEntity(studentDto)));
        return Mapper.studentEntityToStudentDto(studentRepository.findByName(studentDto.getName()));
    }

    @Override
    public List<StudentDto> deleteStudentById(long id) {
        studentRepository.deleteById(id);
        return Mapper.studentEntityToStudentDto(studentRepository.findAll());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student =studentRepository.findByName(username);
        return new User(student.getName(),student.getPassword(),new ArrayList<>());
    }
}
