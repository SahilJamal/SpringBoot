package com.student.mapper;

import com.student.Dto.StudentDto;
import com.student.entity.Student;

import java.util.List;

public class Mapper {

    public static StudentDto studentEntityToStudentDto(Student student){
        if(student.getName() != null){
            StudentDto  studentDto = new StudentDto();
            studentDto.setId(student.getId());
            studentDto.setName(student.getName());
            studentDto.setEmail(student.getEmail());
            studentDto.setPassword(student.getPassword());
            studentDto.setAddress(student.getAddress());
            return studentDto;
        }
        return null;
    }
         public static List<StudentDto> studentEntityToStudentDto(List<Student> student){
          if(!student.isEmpty())
            return  student.stream().map( Mapper::studentEntityToStudentDto).toList();

          return null;
         }

    public static Student studentDtoToStudentEntity(StudentDto studentDto){
        if(studentDto.getName() != null){
            Student  student = new Student();
            student.setId(studentDto.getId());
            student.setName(studentDto.getName());
            student.setEmail(studentDto.getEmail());
            student.setPassword(studentDto.getPassword());
            student.setAddress(studentDto.getAddress());
            return student;
        }
        return null;
    }
    public static List<Student> studentDtoToStudentEntity(List<StudentDto> studentDto){
        if(!studentDto.isEmpty())
            return  studentDto.stream().map( Mapper::studentDtoToStudentEntity).toList();

        return null;
    }

}
