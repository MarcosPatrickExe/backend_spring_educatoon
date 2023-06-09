package com.mouseheroes.educatoon.controllers;

import com.mouseheroes.educatoon.entities.SchoolFamily;
import com.mouseheroes.educatoon.repositories.SchoolFamilyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
//@RequestMapping()
public class SchoolFamilyController {

    @Autowired
    SchoolFamilyRepo schoolFamilyRepo;

    @GetMapping( name="/schoolfamilies")
    public String getAll(){

        System.out.println("GET request detected....");
        return "Hello";
       // return this.schoolFamilyRepo.findAll();
    }
}
