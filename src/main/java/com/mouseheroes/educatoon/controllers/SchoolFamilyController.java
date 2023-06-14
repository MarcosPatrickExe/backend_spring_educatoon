package com.mouseheroes.educatoon.controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mouseheroes.educatoon.entities.SchoolFamily;
import com.mouseheroes.educatoon.repositories.SchoolFamilyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController( )
@RequestMapping(value="/" )
public class SchoolFamilyController {

    @Autowired
    SchoolFamilyRepo schoolFamilyRepo;

    @GetMapping( path="/schoolfamilies")
    public List<SchoolFamily> getAll(){

        System.out.println("GET request detected....");

        List<SchoolFamily> listSchoolFamilyPlan = this.schoolFamilyRepo.findAll();

        listSchoolFamilyPlan.forEach(
             schoolFamily -> {
                 System.out.println("element name: "+schoolFamily.getName() );
             }
        );

     //   JSONPObject jsonpObject = new JSONPObject();
        return listSchoolFamilyPlan;
       // return this.schoolFamilyRepo.findAll();
    }

    @PostMapping( path="/insertSchoolfamily" )
    public JSONPObject insertSchoolFamilyPlan(@RequestBody SchoolFamily schoolFamily){

          System.out.println("obj received: "+schoolFamily.toString() );

          SchoolFamily sf = this.schoolFamilyRepo.save( schoolFamily );

          return new JSONPObject(schoolFamily.toString(), schoolFamily);
    }
}
