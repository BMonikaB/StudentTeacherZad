package com.example.demo.api;

import com.example.demo.domain.Teacher;
import com.example.demo.exception.SchoolException;
import com.example.demo.service.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/school")
public class TeacherApi {

    @Autowired
    private TeacherServiceImpl teacherService;
    @Autowired
    private Environment environment;


    @GetMapping("/teachers")
    public ResponseEntity<List<Teacher>> getAllTeachers() throws SchoolException {
        try {
            List<Teacher> teacherList = teacherService.getAllTeachers();
            return new ResponseEntity<>(teacherList, HttpStatus.OK);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    environment.getProperty(exception.getMessage()), exception);
        }
    }


    @GetMapping("/teachers/{teacherId}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Integer teacherId) throws SchoolException {
        try {
            Teacher teacher = teacherService.getTeacherById(teacherId);
            return new ResponseEntity<>(teacher, HttpStatus.OK);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    environment.getProperty(exception.getMessage()), exception);
        }
    }


    @GetMapping("/teachers/{firstName}/{lastName}")
    public ResponseEntity<Teacher> getStudentByNameAndLastName(@PathVariable String firstName, @PathVariable String lastName) throws SchoolException {
        try {
            Teacher teacher = teacherService.getTeacherByNameAndLastName(firstName, lastName);
            return new ResponseEntity<>(teacher, HttpStatus.OK);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    environment.getProperty(exception.getMessage()), exception);
        }
    }

    //stronicowanie
    @GetMapping("/teachers/pagination/{offset}/{pageSize}")
    public ResponseEntity<List<Teacher>> getAllTeachersWithPaging(@PathVariable int offset, @PathVariable int pageSize) throws SchoolException {
        try {
            List<Teacher> teacherList = teacherService.getAllStudentsWithPaging(offset, pageSize);
            return new ResponseEntity<>(teacherList, HttpStatus.OK);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    environment.getProperty(exception.getMessage()), exception);
        }
    }

    //Sortowanie
    @GetMapping("/teachers/sort/{field}")
    public ResponseEntity<List<Teacher>> getAllTeachersSort(@PathVariable String field) throws SchoolException {
        try {
            List<Teacher> teachertList = teacherService.getAllStudentsSort(field);
            return new ResponseEntity<>(teachertList, HttpStatus.OK);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    environment.getProperty(exception.getMessage()), exception);
        }
    }

    @PostMapping("/teachers")
    public ResponseEntity<String> addTeacher(@Valid @RequestBody Teacher teacher) throws SchoolException {
        try {
            Integer teacherId = teacherService.addTeacher(teacher);
            String successMessage = environment.getProperty("API.INSERT_SUCCESS2") + teacherId;
            return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    environment.getProperty(exception.getMessage()), exception);
        }
    }


    @DeleteMapping("/teachers/{teacherId}")
    public ResponseEntity<String> deleteTeacher(@PathVariable Integer teacherId) throws SchoolException {
        try {
            teacherService.deleteTeacher(teacherId);
            String successMessage = environment.getProperty("API.DELETE_SUCCESS2");
            return new ResponseEntity<>(successMessage, HttpStatus.OK);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    environment.getProperty(exception.getMessage()), exception);
        }
    }


}
