package com.mouseheroes.educatoon.controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mouseheroes.educatoon.entities.SchoolFamily;
import com.mouseheroes.educatoon.repositories.SchoolFamilyRepo;
import com.mouseheroes.educatoon.repositories.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mouseheroes.educatoon.entities.Student;



@RestController
@RequestMapping( name ="/student")
public class StudentController {

    @Autowired
    SchoolFamilyRepo sfrepo;

    @Autowired
    StudentRepo studentRepo;


    @PostMapping( path="/add")
    JSONPObject addStudent(
            @RequestBody Student studentReceived,
            @RequestParam( name="idschoolfamily") String schoolFamilyID
    ){

        if( studentReceived != null ){

            if( this.sfrepo.existsById(  Long.parseLong(schoolFamilyID) )){
                SchoolFamily sf = this.sfrepo.findById( Long.parseLong(schoolFamilyID) ).get();

                studentReceived.setSchoolFamily( sf );
                Student savedStudent = studentRepo.save( studentReceived );

                System.out.println("O NOVO ESTUDANTE '"+savedStudent.getName()+"' DO PLANO Escolar/Familia: '"+ sf.getName() +"' FOI ADICIONANDO COM SUCESSO !!");
                ResponseEntity.status(HttpStatus.CREATED );
                return new JSONPObject( savedStudent.toString(), savedStudent );

            }else{
                System.out.println("O SEGUINTE ID RECEBIDO: '"+schoolFamilyID+"' DO PLANO Família/Escola NÃO EXISTE NO BANCO DE DADOS !!");
                ResponseEntity.status( HttpStatus.EXPECTATION_FAILED );
                return new JSONPObject("O ID "+schoolFamilyID+" DO PLANO Família/Escola NÃO EXISTE NO DATABASE!!", null);
            }

        }else{
            System.out.println("OS DADOS DO NOVO ESTUDANTE NAO PODEM SER VAZIOS !!");
            ResponseEntity.status( HttpStatus.BAD_REQUEST );
            return new JSONPObject("OS DADOS DO NOVO ESTUDANTE NAO PODEM SER VAZIOS !!", null);
        }
    }
}
