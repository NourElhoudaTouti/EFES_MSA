package com.example.microservices.Birth;


import java.time.LocalDate;

public record BirthRegistrationRequest(
     String Nom_child_AR,
     String Nom_child_FR,
     String Prenom_child_AR,
     String Prenom_child_FR,
     LocalDate date_de_naissance_Child,
     String Lieu_naissance,
     String Mere_CIN,
     String Pere_CIN,
     String Adress_ParentFR,
     String Adress_ParentAR
) {
} 
