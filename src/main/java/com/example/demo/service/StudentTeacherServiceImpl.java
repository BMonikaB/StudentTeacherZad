package com.example.demo.service;

import com.example.demo.domain.Student;
import com.example.demo.domain.Teacher;
import com.example.demo.exception.SchoolException;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PostPersist;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class StudentTeacherServiceImpl implements StudentTeacherService{

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    TeacherRepository teacherRepository;


    //dodawanie studenta do ucznia/ucznia do studenta

    public void addStudentToTeacher(Student student, Teacher teacher) throws SchoolException {

        Optional<Student> student1 = studentRepository.findById(student.getId());
        student1.orElseThrow(()->new SchoolException("Service.STUDENT_NOT_FOUND"));
        Optional<Teacher> teacher1 = teacherRepository.findById(teacher.getId());
        teacher1.orElseThrow(()->new SchoolException("Service.TEACHER_NOT_FOUND"));
        student1.get().add(teacher);
    }

    public void removeStudentFromTeacher(Student student, Teacher teacher) throws SchoolException{
        Optional<Student> student1 = studentRepository.findById(student.getId());
        student1.orElseThrow(()->new SchoolException("Service.STUDENT_NOT_FOUND"));
        Optional<Teacher> teacher1 = teacherRepository.findById(teacher.getId());
        teacher1.orElseThrow(()->new SchoolException("Service.TEACHER_NOT_FOUND"));
        student1.get().remove(teacher);
    }






}
