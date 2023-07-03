package com.mouseheroes.educatoon.controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mouseheroes.educatoon.entities.SchoolFamily;
import com.mouseheroes.educatoon.repositories.SchoolFamilyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// PREPARANDO SCHOOL FAMILY ACCOUNT FOR LOGIN
@RestController()
@RequestMapping( value="/schoolfamily" )
public class SchoolFamilyController {

    @Autowired
    SchoolFamilyRepo schoolFamilyRepo;

    @GetMapping(path = "/getall")
    public List<SchoolFamily> getAll() {

        System.out.println("GET request detected....");

        List<SchoolFamily> listSchoolFamilyPlan = this.schoolFamilyRepo.findAll();

        listSchoolFamilyPlan.forEach(
                schoolFamily -> {
                    System.out.println("element name: " + schoolFamily.getName());
                }
        );

        return listSchoolFamilyPlan;
    }


    @PostMapping(path = "/add")
    public JSONPObject insertSchoolFamilyPlan(@RequestBody SchoolFamily newSchoolFamilyPlan) {

        if (newSchoolFamilyPlan == null) {
            String message = "O CORPO/DADOS DA REQUISICAO DO NOVO PLANO ESCOLA/FAMILIA NAO PODE ESTAR VAZIO";
            System.out.println( message);
            ResponseEntity.status(HttpStatus.BAD_REQUEST);
            return new JSONPObject( message, null );

        } else {
            if (newSchoolFamilyPlan.getID_school_family() == null) {

                SchoolFamily schoolFamilySsaved = this.schoolFamilyRepo.save(newSchoolFamilyPlan);
                System.out.println("NOVO ELEMENTO SALVO COM O ID:  " + schoolFamilySsaved.getID_school_family());
                ResponseEntity.status(HttpStatus.CREATED);
                return new JSONPObject(schoolFamilySsaved.toString(), schoolFamilySsaved);
            }
            return null;
        }
    }


    @PostMapping( path = "/login/{loginname}")
    String loginValidate(
        @PathVariable(name = "loginname") String login,
        @RequestBody String password
    ){

        System.out.println("login name: "+login+" // senha: "+password);

        if( this.schoolFamilyRepo.existsByLoginAndPassword(login, password) ){
            ResponseEntity.status( HttpStatus.OK );
            return "Login_success";
        }

        ResponseEntity.status( HttpStatus.NO_CONTENT );
        return "Login_failed";
    }

}
