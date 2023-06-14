package com.mouseheroes.educatoon.repositories;

import com.mouseheroes.educatoon.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TeacherRepositoryRepo extends JpaRepository<Teacher, Long> {

    @Override
    Teacher save( Teacher teacher);
}