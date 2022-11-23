package com.example.demo.service;

import com.example.demo.domain.Student;
import com.example.demo.exception.SchoolException;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService{

    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() throws SchoolException {
        List<Student> students = studentRepository.findAll();
        if(students.isEmpty()) throw  new SchoolException("Service.STUDENTS_NOT_FOUND");
        return students;
    }

    @Override
    public Integer addStudent(Student student){
        Student student1  = studentRepository.save(student);
        return student1.getId();
    }

    @Override
    public void deleteStudent(Integer id) throws SchoolException {
        Optional<Student> studentOptional = studentRepository.findById(id);
        studentOptional.orElseThrow(()->new SchoolException("Service.STUDENT_NOT_FOUND"));
        studentRepository.deleteById(id);
    }

    @Override
    public Student getStudentByNameAndLastName(String firstName, String lastName) throws SchoolException {
        Optional<Student> student2 = studentRepository.findByFirstNameAndLastName(firstName,lastName);
        student2.orElseThrow(()->new SchoolException("Service.STUDENT_NOT_FOUND"));
        return student2.get();
    }

    @Override
    public Student geStudent(Integer id) throws SchoolException {
        Optional<Student> student1 = studentRepository.findById(id);
        if(student1.isEmpty()) throw  new SchoolException("Service.STUDENT_NOT_FOUND");
        return student1.get();
    }

    //Stronnicowanie
    @Override
    public List<Student> getAllStudentsWithPaging(int offset, int pageSize) throws SchoolException {
        Page<Student> students = studentRepository.findAll(PageRequest.of(offset,pageSize));
        if(students.isEmpty()) throw  new SchoolException("Service.STUDENTS_NOT_FOUND");
        return students.stream().toList();
    }

    //Sortowanie
    @Override
    public List<Student> getAllStudentsSort(@PathVariable String field) throws SchoolException {
        List<Student> students = studentRepository.findAll(Sort.by(Sort.Direction.ASC,field));
        if(students.isEmpty()) throw new SchoolException("Service.STUDENTS_NOT_FOUND");
        return students;
    }


}
