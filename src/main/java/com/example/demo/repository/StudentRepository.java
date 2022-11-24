package com.example.demo.repository;

import com.example.demo.domain.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;


public interface StudentRepository extends JpaRepository<Student,Integer> {
    Optional<Student> findByFirstNameAndLastName(String firstName, String lastName);

    //Nie zrozumiałam do konca czy miało być łaczenie tabel - do stronnicowania? jeżeli tak to:
    //@Query("SELECT s FROM student s" + " left join fetch s.teacherSet")
    //List<Student> findAllStudent(Pageable pageable);


}
