package com.example.demo.service;


import com.example.demo.domain.Teacher;
import com.example.demo.exception.SchoolException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeacherService {


    List<Teacher> getAllTeachers() throws SchoolException;
    Teacher getTeacherById(Integer id) throws SchoolException;
    Teacher getTeacherByNameAndLastName(String firstName, String lastName) throws SchoolException;
    List<Teacher> getAllStudentsWithPaging(int offset, int pageSize) throws SchoolException;
    List<Teacher> getAllStudentsSort(String field) throws SchoolException;
    Integer addTeacher(Teacher teacher) throws SchoolException;
    void deleteTeacher(Integer id) throws SchoolException;




}
