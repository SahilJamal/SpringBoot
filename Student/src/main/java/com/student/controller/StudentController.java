package com.student.controller;

import com.student.Dto.StudentDto;
import com.student.service.StudentService;
import com.student.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/student")
public class StudentController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private StudentService studentService;


    @PostMapping(path = "/authenticate")
    public String generateToken(@RequestBody StudentDto studentDto) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(studentDto.getName(), studentDto.getPassword()));
        }catch (Exception e){
            throw new Exception("Invalid UserName Or Password");
        }
        return jwtUtil.generateToken(studentDto.getName());
    }

    @GetMapping
    public List<StudentDto> getAllStudent(){
        return studentService.getAllStudent();
    }

    @GetMapping(path = "/{id}")
    public StudentDto getById(@PathVariable long id){
        return studentService.getById(id);
    }

    @PostMapping
    public StudentDto saveStudent(@RequestBody StudentDto studentDto){
        return studentService.saveStudent(studentDto);
    }

    @PutMapping
    public StudentDto updateStudent(@RequestBody StudentDto studentDto){
        return studentService.updateStudent(studentDto);
    }

    @DeleteMapping(path = "{id}")
    public List<StudentDto> deleteStudent(@PathVariable long id){
        return studentService.deleteStudentById(id);
    }

}
