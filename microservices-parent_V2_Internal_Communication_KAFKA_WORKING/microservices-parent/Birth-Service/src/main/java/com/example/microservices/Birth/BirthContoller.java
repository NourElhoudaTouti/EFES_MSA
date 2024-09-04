package com.example.microservices.Birth;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/birth")
public record BirthContoller(BirthService birthService) {
    @PostMapping
    public void RegisterBirth(@RequestBody BirthRegistrationRequest birthRegistrationRequest){
        System.out.println("birth registered{}");
        birthService.RegisterBirth(birthRegistrationRequest);
    }
    //GET

    @GetMapping //was working
    public java.util.List<Birth> getAllBirths() {
       System.out.println("yes I was here");
        return birthService.getAllBirths();
    }
   
    //DELETE
     @DeleteMapping(path = "{birthId}")
    public void DeleteCitizen(@PathVariable("birthId") Long birthId){
        birthService.DeleteBirths(birthId);
    }

    //update

    @PutMapping(path = "{birthId}/Nom_child_AR")
    public void update_Nom_child_AR(@PathVariable("birthId") Long birthId,@RequestParam(required = false) String Nom_child_AR){
        birthService.update_Nom_child_AR( birthId,Nom_child_AR);
    }
    @PutMapping(path = "{birthId}/Nom_child_FR")
    public void update_Nom_child_FR(@PathVariable("birthId") Long birthId,@RequestParam(required = false) String Nom_child_FR){
        birthService.update_Nom_child_FR( birthId,Nom_child_FR);
    }
    @PutMapping(path = "{birthId}/Prenom_child_AR")
    public void update_Prenom_child_AR(@PathVariable("birthId") Long birthId,@RequestParam(required = false) String Prenom_child_AR){
        birthService.update_Prenom_child_AR( birthId,Prenom_child_AR);
    }
    @PutMapping(path = "{birthId}/Prenom_child_FR")
    public void update_Prenom_child_FR(@PathVariable("birthId") Long birthId,@RequestParam(required = false) String Prenom_child_FR){
        birthService.update_Prenom_child_FR( birthId,Prenom_child_FR);
    }
     @PutMapping(path = "{birthId}/Lieu_naissance")
    public void update_Lieu_naissance(@PathVariable("birthId") Long birthId,@RequestParam(required = false) String Lieu_naissance){
        birthService.update_Lieu_naissance( birthId,Lieu_naissance);
    }
     @PutMapping(path = "{birthId}/Mere_CIN")
    public void update_Mere_CIN(@PathVariable("birthId") Long birthId,@RequestParam(required = false) String Mere_CIN){
        birthService.update_Mere_CIN( birthId,Mere_CIN);
    }
     @PutMapping(path = "{birthId}/Pere_CIN")
    public void update_Pere_CIN(@PathVariable("birthId") Long birthId,@RequestParam(required = false) String Pere_CIN){
        birthService.update_Pere_CIN( birthId,Pere_CIN);
    }
     @PutMapping(path = "{birthId}/Adress_ParentFR")
    public void update_Adress_ParentFR(@PathVariable("birthId") Long birthId,@RequestParam(required = false) String Adress_ParentFR){
        birthService.update_Adress_ParentFR( birthId,Adress_ParentFR);
    }
    @PutMapping(path = "{birthId}/Adress_ParentAR")
    public void update_Adress_ParentAR(@PathVariable("birthId") Long birthId,@RequestParam(required = false) String Adress_ParentAR){
        birthService.update_Adress_AarentFR( birthId,Adress_ParentAR);
    }
     @PutMapping(path = "{birthId}/date_de_naissance_Child")
    public void update_date_de_naissance_Child(@PathVariable("birthId") Long birthId,@RequestParam(required = false) LocalDate date_de_naissance_Child){
        birthService.update_date_de_naissance_Child( birthId,date_de_naissance_Child);
    }
} 
   

