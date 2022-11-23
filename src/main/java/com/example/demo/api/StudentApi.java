package com.example.demo.api;

import com.example.demo.domain.Student;
import com.example.demo.exception.SchoolException;
import com.example.demo.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/school")
public class StudentApi {

    @Autowired
    private StudentServiceImpl studentService;
    @Autowired
    private Environment environment;




    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() throws SchoolException {
        try{
            List<Student> studentList = studentService.getAllStudents();
            return new ResponseEntity<>(studentList, HttpStatus.OK);
        }
        catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    environment.getProperty(exception.getMessage()),exception);
        }
    }

    //stronicowanie
    //http://localhost:8080/school/students/posts?page=0
    @GetMapping("/students/pagination/{offset}/{pageSize}")
    public ResponseEntity<List<Student>> getAllStudentsWithPaging(@PathVariable int offset,@PathVariable int pageSize) throws SchoolException {

        try{
            List<Student> studentList = studentService.getAllStudentsWithPaging(offset,pageSize);
            return new ResponseEntity<>(studentList, HttpStatus.OK);
        }
        catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    environment.getProperty(exception.getMessage()),exception);
        }
    }

    //Sortowanie
    @GetMapping("/students/sort/{field}")
    public ResponseEntity<List<Student>> getAllStudentsSort(@PathVariable String field) throws SchoolException {
        try{
            List<Student> studentList = studentService.getAllStudentsSort(field);
            return new ResponseEntity<>(studentList, HttpStatus.OK);
        }
        catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    environment.getProperty(exception.getMessage()),exception);
        }
    }






    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id) throws SchoolException {
        try {
            Student student = studentService.geStudent(id);
            return new ResponseEntity<>(student, HttpStatus.OK);
        }catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    environment.getProperty(exception.getMessage()),exception);
        }
    }



    //wyszukiwanie po imieniu i nazwisku
    @GetMapping("/students/{firstName}/{lastName}")
    public ResponseEntity<Student> getStudentByNameAndLastName(@PathVariable String firstName, @PathVariable String lastName) throws SchoolException {
        try {
            Student student = studentService.getStudentByNameAndLastName(firstName, lastName);
            return new ResponseEntity<>(student, HttpStatus.OK);
        }catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    environment.getProperty(exception.getMessage()),exception);
        }
    }

    //tworzenie
    @PostMapping("/students")
    public ResponseEntity<String> addStudent(@Valid @RequestBody Student student) {
        try {
            Integer studentId = studentService.addStudent(student);
            String successMessage = environment.getProperty("API.INSERT_SUCCESS") + studentId;
            return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
        }catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    environment.getProperty(exception.getMessage()),exception);
        }
    }

    //usuwanie
    @DeleteMapping("/students/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable Integer studentId) throws SchoolException {
        try {
            studentService.deleteStudent(studentId);
            String successMessage = environment.getProperty("API.DELETE_SUCCESS");
            return new ResponseEntity<>(successMessage, HttpStatus.OK);
        }catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    environment.getProperty(exception.getMessage()),exception);
        }
    }

    //edytownie



}
