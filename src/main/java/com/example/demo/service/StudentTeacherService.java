package com.example.demo.service;

import com.example.demo.domain.Student;
import com.example.demo.domain.Teacher;
import com.example.demo.exception.SchoolException;
import org.springframework.stereotype.Service;

@Service
public interface StudentTeacherService {


    public void addStudentToTeacher(Student student, Teacher teacher) throws SchoolException;
    public void removeStudentFromTeacher(Student student, Teacher teacher) throws SchoolException;
}
