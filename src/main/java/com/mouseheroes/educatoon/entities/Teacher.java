package com.mouseheroes.educatoon.entities;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
// import javax.persistence.Table;



@Getter
@Setter
@Entity
@Table( name= "teachers" )
public class Teacher {//  extends SchoolFamily

    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY )
    @Column(name = "ID_teacher")
    private Long ID_Teacher;

    @ManyToOne( fetch = FetchType.LAZY, optional= false) // targetEntity = SchoolFamily.class
    @JoinColumn(name = "ID_school_family", nullable = false)
    private SchoolFamily schoolFamily;

    @Column( columnDefinition = "character varying", length = 100, nullable = false )
    private String name;

    @Column( columnDefinition = "character varying", length = 30, nullable = false )
    private String discipline;
}
