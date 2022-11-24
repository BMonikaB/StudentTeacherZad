package com.example.demo.service;

import com.example.demo.domain.Student;
import com.example.demo.exception.SchoolException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public interface StudentService {


    List<Student> getAllStudents() throws SchoolException;
    public Student geStudent(Integer id) throws SchoolException;
    public Student getStudentByNameAndLastName(String firstName, String lastName) throws SchoolException;
    public List<Student> getAllStudentsWithPaging(int offset, int pageSize) throws SchoolException;
    public List<Student> getAllStudentsSort(String field) throws SchoolException;
    public Integer addStudent(Student student) throws SchoolException;
    public void deleteStudent(Integer id) throws SchoolException;


}
