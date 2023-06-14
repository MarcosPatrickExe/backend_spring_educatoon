package com.mouseheroes.educatoon.entities;

import java.io.Serializable;
import java.util.*;
import java.time.LocalDate;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table( name="school_families")
//@Inheritance( strategy = InheritanceType.TABLE_PER_CLASS )
public class SchoolFamily {// implements Serializable

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name="ID_school_family" )
    private Long ID_school_family;

    @Column(  columnDefinition= "varchar", length = 80, nullable = false )
    private String name;

    @Column( name="CNPJ", columnDefinition= "varchar",  nullable= true)
    private String CNPJ;

    @Column( name="CPF", columnDefinition = "bigint", nullable = true)
    private String CPF;

    @Column( name="plan_type", columnDefinition= "varchar", length=10, nullable = false)
    private String planType;

    @Column( name="date_of_signature", columnDefinition= "date", nullable = false)
    private LocalDate dateOfSignature;

    @Column( name="plan_value", columnDefinition= "real", nullable = false)
    private double planValue;

    @Column( columnDefinition= "date", nullable = false)
    private LocalDate payday;

    @Column( columnDefinition= "varchar", length = 30, nullable = true)
    private String state;

    @Column( columnDefinition= "varchar", length = 30, nullable = true)
    private String city;

    @Column( name="street_name", columnDefinition= "varchar", length = 50, nullable = true)
    private String streetName;

    @Column( columnDefinition= "varchar", length=20, nullable = false)
    private String login;

    @Column( columnDefinition= "varchar", length=50, nullable = false)
    private String email;

    @Column( columnDefinition = "bigint", length=20, nullable = false)
    private String password;

    @Column( columnDefinition= "bigint", nullable = true)
    private String phone;

    @Column( columnDefinition= "bigint", nullable = false)
    private String CEP;

    @OneToMany( mappedBy = "schoolFamily", cascade = CascadeType.ALL ) //targetEntity = Teacher.class
    private List<Teacher> teachers = new ArrayList<Teacher>();

/*    @OneToMany( targetEntity = Student.class )
  //  @JoinColumn( name="ID_school_family" )
    private List<Student> students;

    @OneToMany( targetEntity = FamilyMember.class )
   // @JoinColumn( name="ID_school_family" )
    private List<FamilyMember> familyMembers;*/
}
