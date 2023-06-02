package com.mouseheroes.educatoon.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;


public class SchoolFamily {

 //   @PrimaryGeneratedColumn() // chave-primaria e auto-incremento
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long ID_school_family;

    @Column( name ="bigint", nullable= true )
    private String name;

    @Column(  name ="bigint", nullable= true)
    private String CNPJ;

    @Column( columnDefinition = "varchar", length = 255)
    private String CPF;

 //   @Column({ type: 'character varying', length: 10})
    private String plan_type;

//    @Column({ type: 'date' })
    private Date dateOfSignature;

//    @Column({ type: 'real' })
    private double planValue;

 //   @Column({ type: 'date' })
    private Date payday;

//    @Column({ type: 'character varying', nullable :true, length: 30})
    @Column(nullable = false)
    private String state;

//    @Column({ type: "character varying", nullable :true, length: 30 })
    private String city;

 //   @Column({type: 'character varying', nullable :true, length: 50 })
    private String streetName;

 //   @Column()
    private  String login;

 //   @Column()
    private  String email;

 //   @Column()
    private  String password;

//    @Column({ type: 'bigint', nullable: true })
    private String phone;

 //   @Column({ type: 'bigint'})
    private  String CEP;

 //   @OneToMany(() => Student, (student :Student) => student.schoolFamily  )
 //   students :Student[];

//    @OneToMany(()=> Teacher, (teacher :Teacher) => teacher.schoolFamily )
 //   teachers: Teacher[];

//    @OneToMany(()=> FamilyMember, ( fm :FamilyMember) => fm.schoolFamily )
 //   familyMembers :FamilyMember[];

}
