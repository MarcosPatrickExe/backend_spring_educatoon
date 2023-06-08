package com.mouseheroes.educatoon.entities;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;


@Getter
@Setter
@Entity
@Table( name= "family_members" )
public class FamilyMember extends SchoolFamily {
// Aqui infelizmente está ocorrendo um erro de cast da super class: "SinglePage" para "subClass"
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name="ID_family_member" )
    private Long IDfamilyMember ;

    @ManyToOne( targetEntity = SchoolFamily.class, optional = false )
    @JoinColumn( name = "ID_school_family", nullable = false )
    private SchoolFamily schoolFamily;

    @Column( columnDefinition = "character varying", length =100, nullable = false )
    private String name;

    @Column( columnDefinition = "int", nullable = false )
    private Integer progress;
}
