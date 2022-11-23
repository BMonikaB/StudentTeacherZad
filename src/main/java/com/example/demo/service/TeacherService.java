package com.example.demo.service;

import com.example.demo.domain.Student;
import com.example.demo.domain.Teacher;
import com.example.demo.exception.SchoolException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeacherService {


    List<Teacher> getAllTeachers() throws SchoolException;
    public Integer addTeacher(Teacher teacher) throws SchoolException;
    public void deleteTeacher(Integer id) throws SchoolException;
    public Teacher getTeacherByNameAndLastName(String firstName, String lastName) throws SchoolException;
    public Teacher getTeacherById(Integer id) throws SchoolException;
    public List<Teacher> getAllStudentsWithPaging(int offset, int pageSize) throws SchoolException;
    public List<Teacher> getAllStudentsSort(String field) throws SchoolException;



}
