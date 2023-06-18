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



@RestController
public class FamilyMemberController {

    @Autowired
    FamilyMemberRepo fmrepo;
    SchoolFamilyRepo sfrepo;


    @GetMapping(name = "/getAllFamilyMembers")
    List<FamilyMember> getAll(){

        List<FamilyMember> allFM = this.fmrepo.findAll();

        if( allFM == null){
            System.out.println("NENHUMA CONTA DE MEMBRO DE FAMILIA ENCONTRADO....");
            ResponseEntity.status( HttpStatus.NO_CONTENT );
            return null;
        }

        System.out.println("DADOS DE TODOS OS MEMBROS DE FAMILIA FORAM RETORNADOS !!");
        ResponseEntity.status( HttpStatus.OK );
        return allFM;
    }



    @PostMapping(name = "/insertFamilyMember")
    JSONPObject saveFamilyMember(
            @RequestBody FamilyMember newFM,
            @RequestParam( name="idschoolfamily") String schoolFamilyPlanID
    ){

        if( newFM != null ){

             if( this.sfrepo.existsById( Long.parseLong(schoolFamilyPlanID) ) ){

                 SchoolFamily sf = this.sfrepo.getById( Long.parseLong(schoolFamilyPlanID) );
                 newFM.setSchoolFamily( sf );
                 FamilyMember savedFamilyMember = fmrepo.save( newFM );

                 System.out.println("O NOVO MEMBRO "+newFM.getName()+" DO PLANO Escolar/Familia: '"+ sf.getName() +"' FOI ADICIONANDO COM SUCESSO !!");
                 ResponseEntity.status( HttpStatus.CREATED );
                 return new JSONPObject( savedFamilyMember.toString(), savedFamilyMember);

             }else{
                 ResponseEntity.status( HttpStatus.EXPECTATION_FAILED );
                 System.out.println("O PLANO COM O ID RECEBIDO: '"+schoolFamilyPlanID+"' NAO EXISTE NO BANCO DE DADOS.... ");
             }

        }else{
            System.out.println("OS DADOS DO NOVO MEMBRO DA FAMILIA NAO PODEM SER VAZIOS !!");
            ResponseEntity.status( HttpStatus.BAD_REQUEST );
        }

        return null;
    }
}
