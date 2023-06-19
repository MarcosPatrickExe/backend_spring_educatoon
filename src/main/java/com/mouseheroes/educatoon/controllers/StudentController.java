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
import java.util.List;



@RestController
@RequestMapping( value="/student")
public class StudentController {

    @Autowired
    SchoolFamilyRepo sfrepo;

    @Autowired
    StudentRepo studentRepo;


    @GetMapping( path="/getall" )
    List<Student> getAllStudents(){
        System.out.println("ALL STUDENTS WERE REQUESTED !!");

        List<Student> allStudents = this.studentRepo.findAll();

        if( allStudents.isEmpty() ){
            System.out.println("NENHUMA CONTA DE ESTUDANTE ENCONTRADA.... !!");
            ResponseEntity.status( HttpStatus.NO_CONTENT );
            return null;
        }

        System.out.println("TODOS OS ESTUDANTES FORAM RETORNADOS !!");
        ResponseEntity.status( HttpStatus.OK );
        return allStudents;
    }


    @PostMapping( path="/add")
    JSONPObject addStudent(
            @RequestBody Student studentReceived,
            @RequestParam( name="idschoolfamily") String schoolFamilyID
    ){

        if( studentReceived != null ){

            if( schoolFamilyID.equals("") ){
                System.out.println("O PARAMETRO 'idschoolfamily' RECEBIDO NAO PODE SER VAZIO !!");
                return new JSONPObject("O PARAMETRO 'idschoolfamily' RECEBIDO NAO PODE SER VAZIO !!", null);

            }else if( this.sfrepo.existsById(  Long.parseLong(schoolFamilyID) )){
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
