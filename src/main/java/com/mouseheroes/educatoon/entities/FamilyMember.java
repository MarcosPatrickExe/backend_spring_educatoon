package com.mouseheroes.educatoon.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;

// preparando essa entidade para fazer o login

@Getter
@Setter
@Entity
@Table( name= "family_members" )
//@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class FamilyMember{ // implements Serializable{
// Aqui estava ocorrendo um erro de cast da super class: "SinglePage" para "subClass"
    
    @Id
    @SequenceGenerator( name="family_member_generator", initialValue = 4, allocationSize = 1)
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator="family_member_generator")
    @Column( name="ID_family_member" )
    private Long id_family_member ;

    @ManyToOne( fetch = FetchType.LAZY, optional = false) // targetEntity = SchoolFamily.class, optional = false
    @JsonIgnore // this annotation fix the error 'Cannot call sendError() after the response has been committed'
    @JoinColumn( name = "ID_school_family", nullable = false )
    private SchoolFamily schoolFamily; //ID_school_family

    @Column( columnDefinition = "varchar", length =100, nullable = false )
    private String name;

    @Column( columnDefinition = "bigint", nullable = false )
    private Integer progress;
}

