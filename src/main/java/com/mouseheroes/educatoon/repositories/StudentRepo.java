package com.mouseheroes.educatoon.repositories;
import com.mouseheroes.educatoon.entities.Student;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface StudentRepo extends JpaRepository<Student, Long> {

    @Override
    Student save( Student newStudent);

    @Override
    List<Student> findAll();
}