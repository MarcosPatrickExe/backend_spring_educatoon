package com.mouseheroes.educatoon.entities;


import javax.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
//import org.springframework.data.annotation.Id;

@Getter
@Setter
@Entity // @Entity( name= "students") // Ja tentei remover as Tables e adicionalas para resolver o problema mas infelizmenente ainda N consegui resolver....
@Table( name= "students" )
public class Student {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long enrollment; //enrollment = inscricao/matricula

    @ManyToOne( fetch = FetchType.LAZY, optional = false ) //targetEntity = SchoolFamily.class,
    @JsonIgnore //this annotation fix the error 'Cannot call sendError() after the response has been committed'
    @JoinColumn( name= "ID_school_family", nullable = false )
    private SchoolFamily schoolFamily; //ID_school :number;

    @Column( columnDefinition="character varying", length = 100, nullable = false )
    private String name;

    @Column( columnDefinition ="int", nullable = false )
    private String progress;

  //  @OneToMany( targetEntity = Doubt.class )
 //   @JoinColumn ( name ="enrollment")
  //  private List<Doubt> doubts;
}

