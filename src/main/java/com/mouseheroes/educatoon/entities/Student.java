package com.mouseheroes.educatoon.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.springframework.data.annotation.Id;


@Entity
@Table(name= "students" )

public class Student {

    @Id
    int enrollment; //enrollment = inscricao/matricula

    SchoolFamily schoolFamily; //ID_school :number;

    String name;

    String progress;
}

