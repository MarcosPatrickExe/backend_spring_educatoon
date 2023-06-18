package com.mouseheroes.educatoon.repositories;
import com.mouseheroes.educatoon.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepo extends JpaRepository<Student, Long> {

    @Override
    Student save( Student newStudent);
}