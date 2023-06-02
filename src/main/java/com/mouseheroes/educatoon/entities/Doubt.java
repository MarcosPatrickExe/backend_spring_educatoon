package com.mouseheroes.educatoon.entities;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalTime;
import java.time.LocalDate;


@Getter
@Setter
@Entity( name = "doubts")
public class Doubt extends SchoolFamily {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long IDdoubt;

    @Column( name = "doubt", columnDefinition = "text", nullable = false)
    private String text;

    @Column(columnDefinition = "time", nullable = false)
    private LocalTime time;

    @Column( columnDefinition = "date", nullable = false )
    private LocalDate date;

    @ManyToOne( targetEntity = Student.class )
    @JoinColumn( name="enrollment_student", nullable = false )
    private Student student;
}