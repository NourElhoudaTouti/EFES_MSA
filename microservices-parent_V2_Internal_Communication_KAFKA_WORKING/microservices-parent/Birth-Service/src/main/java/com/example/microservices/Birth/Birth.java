package com.example.microservices.Birth;

import java.time.LocalDate;

import javax.persistence.*;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.SequenceGenerator;
//import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
//@Builder
@NoArgsConstructor
public class Birth {
    @Id
    @SequenceGenerator(
        name="birth_sequence",
        sequenceName="birth_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.IDENTITY,
        generator="birth_sequence"
        )
    Long id;
    private String Nom_child_AR;
    private String Nom_child_FR;
    private String Prenom_child_AR;
    private String Prenom_child_FR;
    private LocalDate date_de_naissance_Child;
    private String Lieu_naissance;
    private String Mere_CIN;
    private String Pere_CIN;
    private String Adress_ParentFR;
    private String Adress_ParentAR;

     @Builder
    public Birth(String Nom_child_AR, String Nom_child_FR,String Prenom_child_AR,String Prenom_child_FR, LocalDate date_de_naissance_Child, String Lieu_naissance,String Mere_CIN, String Pere_CIN, String Adress_ParentFR, String Adress_ParentAR) {
        this.Nom_child_AR = Nom_child_AR;
        this.Nom_child_FR = Nom_child_FR;
        this.Prenom_child_AR=Prenom_child_AR;
        this.Prenom_child_FR=Prenom_child_FR;
        this.date_de_naissance_Child = date_de_naissance_Child;
        this.Lieu_naissance=Lieu_naissance;
        this.Mere_CIN = Mere_CIN;
        this.Pere_CIN = Pere_CIN;
        this.Adress_ParentFR = Adress_ParentFR;
        this.Adress_ParentAR = Adress_ParentAR;
    }
    
}
