package com.mouseheroes.educatoon.repositories;

import com.mouseheroes.educatoon.entities.SchoolFamily;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface SchoolFamilyRepo extends JpaRepository<SchoolFamily, Long> {

    @Override
    List<SchoolFamily> findAll();

}