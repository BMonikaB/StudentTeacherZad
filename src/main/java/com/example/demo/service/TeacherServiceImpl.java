package com.example.demo.service;

import com.example.demo.domain.Teacher;
import com.example.demo.exception.SchoolException;
import com.example.demo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService{

    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public List<Teacher> getAllTeachers() throws SchoolException {
        List<Teacher> teachers = teacherRepository.findAll();
        if(teachers.isEmpty()) throw  new SchoolException("Service.TEACHERS_NOT_FOUND");
        return teachers;
    }
    @Override
    public Teacher getTeacherById(Integer id) throws SchoolException {
        Optional<Teacher> teacher1 = teacherRepository.findById(id);
        if(teacher1.isEmpty()) throw new SchoolException("Service.TEACHER_NOT_FOUND");
        return teacher1.get();
    }
    @Override
    public Teacher getTeacherByNameAndLastName(String firstName, String lastName) throws SchoolException {
        Optional<Teacher> teacher = teacherRepository.findByFirstNameAndLastName(firstName,lastName);
        teacher.orElseThrow(()->new SchoolException("Service.TEACHER_NOT_FOUND"));
        return teacher.get();
    }
    //stronicowanie
    @Override
    public List<Teacher> getAllStudentsWithPaging(int offset, int pageSize) throws SchoolException {
        Page<Teacher> teachers = teacherRepository.findAll(PageRequest.of(offset,pageSize));
        if(teachers.isEmpty()) throw new SchoolException("Service.TEACHERS_NOT_FOUND");
        return teachers.stream().toList();
    }
    //sortowanie
    @Override
    public List<Teacher> getAllStudentsSort(String field) throws SchoolException {
        List<Teacher> teachers = teacherRepository.findAll(Sort.by(Sort.Direction.ASC,field));
        if(teachers.isEmpty()) throw  new SchoolException("Service.TEACHERS_NOT_FOUND");
        return teachers;
    }
    @Override
    public Integer addTeacher(Teacher teacher) throws SchoolException {
        Teacher teacher1  = teacherRepository.save(teacher);
        return teacher1.getId();
    }
    @Override
    public void deleteTeacher(Integer id) throws SchoolException {
        Optional<Teacher> teacherOptional = teacherRepository.findById(id);
        teacherOptional.orElseThrow(()->new SchoolException("Service.TEACHER_NOT_FOUND"));
        teacherRepository.deleteById(id);
    }






}
