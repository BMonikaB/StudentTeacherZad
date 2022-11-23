package com.example.demo.repository;

import com.example.demo.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher,Integer> {

    Optional<Teacher> findByFirstNameAndLastName(String firstName, String lastName);
}
