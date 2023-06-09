package com.example.demo.modele;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter 
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pokemon {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Column(nullable = false)
    private String name;


    @Column(nullable = false)
    private String type;


    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="veterinaire_id")
    private Veterinaire veterinaire;
}
