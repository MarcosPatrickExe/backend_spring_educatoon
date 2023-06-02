package com.mouseheroes.educatoon.entities;


import javax.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
//import org.springframework.data.annotation.Id;


@Getter
@Setter
@Entity( name= "students")
public class Student {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long enrollment; //enrollment = inscricao/matricula

    @ManyToOne( targetEntity = SchoolFamily.class )
    @JoinColumn( name= "ID_school_family", nullable = false )
    private SchoolFamily schoolFamily; //ID_school :number;

    @Column( columnDefinition = "character varying", length = 100, nullable = false )
    private String name;

    @Column( columnDefinition ="int", nullable = false )
    private String progress;

    @OneToMany( targetEntity = Doubt.class )
    @JoinColumn ( name ="enrollment")
    private List<Doubt> doubts;
}

