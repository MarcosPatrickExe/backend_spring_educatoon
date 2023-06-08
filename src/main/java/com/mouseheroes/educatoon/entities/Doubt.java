package com.mouseheroes.educatoon.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.time.LocalTime;
import java.time.LocalDate;


@Getter
@Setter
@Entity
@Table( name="doubts" )
public class Doubt extends Student {

    @Id
    @GeneratedValue
    private Long IDdoubt;

    @Column( name = "doubt", columnDefinition = "text", nullable = false)
    private String text;

    @Column(columnDefinition = "time", nullable = false)
    private LocalTime time;

    @Column( columnDefinition = "date", nullable = false )
    private LocalDate date;

    @ManyToOne( targetEntity = Student.class, optional =false )
    @JoinColumn( name="student_enrollment", nullable = false )
    private Student student;
}