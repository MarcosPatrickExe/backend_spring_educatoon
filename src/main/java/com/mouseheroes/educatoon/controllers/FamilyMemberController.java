package com.mouseheroes.educatoon.controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mouseheroes.educatoon.entities.FamilyMember;
import com.mouseheroes.educatoon.entities.SchoolFamily;
import com.mouseheroes.educatoon.repositories.FamilyMemberRepo;
import com.mouseheroes.educatoon.repositories.SchoolFamilyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping( value="/member")
public class FamilyMemberController {

    @Autowired
    FamilyMemberRepo fmrepo;
    @Autowired
    SchoolFamilyRepo sfrepo;


    @GetMapping( path = "/getAllMembers")
    List<FamilyMember> getAll(){

        System.out.println("getAllFamilyMembers REQUISITED !!");

        List<FamilyMember> allFM = this.fmrepo.findAll();

        if( allFM == null){
            System.out.println("NENHUMA CONTA DE MEMBRO DE FAMILIA ENCONTRADO....");
            ResponseEntity.status( HttpStatus.NO_CONTENT );
            return null;
        }

        System.out.println("DADOS DE TODOS OS MEMBROS DE FAMILIA FORAM RETORNADOS !!");
        //ResponseEntity.status( HttpStatus.OK );
        return allFM;
    }



    @PostMapping(path = "/insertFamilyMember")
    JSONPObject saveFamilyMember(
            @RequestBody FamilyMember newFM,
            @RequestParam( name="idschoolfamily") String schoolFamilyPlanID
    ){

        System.out.println("insertFamilyMember REQUISITED WITH ID OF SchoolFamily: "+schoolFamilyPlanID);

        if( newFM != null ){
             if( schoolFamilyPlanID.equals("") ){
                 System.out.println("O PARAMETRO 'idschoolfamily' PASSADO NAO PODE SER VAZIO !!");
                 return new JSONPObject("O PARAMETRO 'idschoolfamily' PASSADO NAO PODE SER VAZIO !!", null);

             }else if( this.sfrepo.existsById( Long.parseLong(schoolFamilyPlanID) ) ){
                 Optional<SchoolFamily> sf = this.sfrepo.findById( Long.parseLong(schoolFamilyPlanID) );
                 newFM.setSchoolFamily( sf.get() );
                 FamilyMember savedFamilyMember = fmrepo.save( newFM );

                 System.out.println("O NOVO MEMBRO '"+newFM.getName()+"' DO PLANO Escolar/Familia: '"+ sf.get().getName() +"' FOI ADICIONANDO COM SUCESSO !!");
                 ResponseEntity.status( HttpStatus.CREATED );
                 return new JSONPObject( savedFamilyMember.toString(), savedFamilyMember);

             }else{
                 ResponseEntity.status( HttpStatus.EXPECTATION_FAILED );
                 System.out.println("O PLANO COM O ID RECEBIDO: '"+schoolFamilyPlanID+"' NAO EXISTE NO BANCO DE DADOS.... ");
                 return new JSONPObject("O PLANO COM O ID RECEBIDO: '"+schoolFamilyPlanID+"' NAO EXISTE NO DATABASE", null);
             }

        }else{
            System.out.println("OS DADOS DO NOVO MEMBRO DA FAMILIA NAO PODEM SER VAZIOS !!");
            ResponseEntity.status( HttpStatus.BAD_REQUEST );
            return new JSONPObject("OS DADOS DO NOVO MEMBRO DA FAMILIA NAO PODEM SER VAZIOS !!", null);
        }
    }
}

