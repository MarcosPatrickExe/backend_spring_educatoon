package com.mouseheroes.educatoon.controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mouseheroes.educatoon.entities.SchoolFamily;
import com.mouseheroes.educatoon.repositories.SchoolFamilyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController()
@RequestMapping( value="/schoolfamily" )
public class SchoolFamilyController {

    @Autowired
    SchoolFamilyRepo schoolFamilyRepo;

    @GetMapping( path="/getall")
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

    @PostMapping( path="/add" )
    public JSONPObject insertSchoolFamilyPlan( @RequestBody SchoolFamily newSchoolFamilyPlan ){

          if( newSchoolFamilyPlan.getID_school_family() == null ){

              List<SchoolFamily> allPlans = this.schoolFamilyRepo.findAll();
              SchoolFamily lastPlan = allPlans.get( allPlans.size()-1 );
              Long lastCurrentId = lastPlan.getID_school_family();
              System.out.println("ID do ultimo elemento: "+ lastCurrentId);

              newSchoolFamilyPlan.setID_school_family( ++lastCurrentId );
              SchoolFamily schoolFamilySsaved = this.schoolFamilyRepo.save( newSchoolFamilyPlan );
              System.out.println("NOVO ELEMENTO SALVO COM O ID:  "+ schoolFamilySsaved.getID_school_family() );
              return new JSONPObject(newSchoolFamilyPlan.toString(), schoolFamilySsaved);
          }else{
              System.out.println("O NOVO ELEMENTO COM ID "+newSchoolFamilyPlan.getID_school_family()+"JA EXISTE !!");
          }

          return null;
    }
}
