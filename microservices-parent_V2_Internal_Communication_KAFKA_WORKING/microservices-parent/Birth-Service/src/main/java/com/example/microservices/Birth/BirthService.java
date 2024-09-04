package com.example.microservices.Birth;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
//import jakarta.transaction.Transactional;
import javax.transaction.Transactional;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


@Transactional
@Service
public class BirthService {

    private final  BirthRepository birthRepository;
    private final RestTemplate restTemplate;

    private final KafkaTemplate<String, String> kafkaTemplate;


    public BirthService(BirthRepository birthRepository, RestTemplate restTemplate, KafkaTemplate<String, String> kafkaTemplate){
       this.birthRepository=birthRepository;
        this.restTemplate = restTemplate;
        this.kafkaTemplate=kafkaTemplate;
    }


    public  void RegisterBirth(BirthRegistrationRequest request) {
        System.out.println("wow");
        Birth birth = Birth.builder()
                .Prenom_child_FR(request.Prenom_child_FR())
                .Prenom_child_AR(request.Prenom_child_AR())
                .Nom_child_AR(request.Nom_child_AR())
                .Nom_child_FR(request.Nom_child_FR())
                .date_de_naissance_Child(request.date_de_naissance_Child())
                .Lieu_naissance(request.Lieu_naissance())
                .Pere_CIN(request.Pere_CIN())
                .Mere_CIN(request.Mere_CIN())
                .Adress_ParentAR(request.Adress_ParentAR())
                .Adress_ParentFR(request.Adress_ParentFR())
                .build();
        // System.out.println("Saving birth: " + birth);

        // call citizen service and register a birth if the mother and father exist
        //newly done
        String citizenCIN = request.Pere_CIN();
        System.out.println("the citizen is" + citizenCIN);
      // String citizenExistenceUrl = "http://CITIZEN-SERVICE/api/citizen/checkExistence/" + citizenCIN;
      // String citizenExistenceUrl = "http://CITIZEN-MICROSERVICE_SERVICE/api/citizen/checkExistence/" + citizenCIN;
       String citizenExistenceUrl = "http://localhost:8099/api/citizen/checkExistence/" + citizenCIN;
        // Make a request to Citizen microservice to check if the citizen exists
        Boolean citizenExists = restTemplate.getForObject(citizenExistenceUrl, Boolean.class);
        if (citizenExists != null && citizenExists) {
            System.out.println("Saving birth: " + birth);
            birthRepository.save(birth);

            // adding asynch communication

            // Convert the birth object to a message string (e.g., JSON)
           //NOW DID IT  String birthMessage = convertBirthToMessage(birth);

            // Send the message to Kafka topic
          // NOW DID IT   kafkaTemplate.send("BirthTopic", birthMessage);
            // was working from here till throw exception throw new IllegalArgumentException("Citizen with CIN " + citizenCIN + " does not exist.");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            try {
                String birthJson = objectMapper.writeValueAsString(birth);
                kafkaTemplate.send("BirthTopic", birthJson);
            } catch (IOException e) {
                // Handle serialization exception
            }
        } else {
            throw new IllegalArgumentException("Citizen with CIN " + citizenCIN + " does not exist.");
        }


        /*CitizenCheckResponse citizenCheckResponse=restTemplate.getForObject(
                "http:localhost:8080/api/citizen/checkExistence/{cin}",
                CitizenCheckResponse.class,
                birth.getMere_CIN()
        );
        if(CitizenCheckResponse.isExist())
            throw new IllegalStateException("Citizen don't exist");*/
            //System.out.println("birth saved successfully");
        }
    /*private String convertBirthToMessage(Birth birth) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(birth);
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert birth to JSON", e);
        }
    }*/


    private String convertBirthToMessage(Birth birth) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Register JavaTimeModule
        try {
            System.out.println("this is the birth"+birth);
            return objectMapper.writeValueAsString(birth);
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert birth to JSON", e);
        }
    }



    //GET
    public List<Birth> getAllBirths(){
        return birthRepository.findAll();
    }

    //DELETE

    public void DeleteBirths(Long birthId){
        boolean exists= birthRepository.existsById(birthId);
        if (!exists){
            throw new IllegalStateException(
                "citizen with Id"+ birthId +"does not exist");
        }
        birthRepository.deleteById(birthId);


    }

    //UPDATE
    @Transactional
    public Birth update_Nom_child_AR(Long birthId, String nom_child_AR) {
        Birth birth = birthRepository.findById(birthId)
        .orElseThrow(() -> new IllegalArgumentException("Citizen not found with id: " + birthId));

        birth.setPrenom_child_AR(nom_child_AR);
        return birthRepository.save(birth);
    }


    @Transactional
    public Birth update_Nom_child_FR(Long birthId, String nom_child_FR) {
        Birth birth = birthRepository.findById(birthId)
        .orElseThrow(() -> new IllegalArgumentException("Citizen not found with id: " + birthId));

        birth.setPrenom_child_FR(nom_child_FR);
        return birthRepository.save(birth);
    }

    @Transactional
    public Birth update_Prenom_child_AR(Long birthId, String prenom_child_AR) {
        Birth birth = birthRepository.findById(birthId)
        .orElseThrow(() -> new IllegalArgumentException("Citizen not found with id: " + birthId));

        birth.setPrenom_child_AR(prenom_child_AR);
        return birthRepository.save(birth);
    }


    @Transactional
    public Birth update_Prenom_child_FR(Long birthId, String prenom_child_FR) {
        Birth birth = birthRepository.findById(birthId)
        .orElseThrow(() -> new IllegalArgumentException("Citizen not found with id: " + birthId));

        birth.setPrenom_child_FR(prenom_child_FR);
        return birthRepository.save(birth);
    }


    @Transactional
    public Birth update_date_de_naissance_Child(Long birthId, LocalDate date_de_naissance_Child) {
        Birth birth = birthRepository.findById(birthId)
        .orElseThrow(() -> new IllegalArgumentException("Citizen not found with id: " + birthId));

        birth.setDate_de_naissance_Child(date_de_naissance_Child);
        return birthRepository.save(birth);
    }


    @Transactional
    public Birth update_Lieu_naissance(Long birthId, String lieu_naissance) {
        Birth birth = birthRepository.findById(birthId)
        .orElseThrow(() -> new IllegalArgumentException("Citizen not found with id: " + birthId));

        birth.setLieu_naissance(lieu_naissance);
        return birthRepository.save(birth);
    }


    @Transactional
    public Birth update_Mere_CIN(Long birthId, String mere_CIN) {
        Birth birth = birthRepository.findById(birthId)
        .orElseThrow(() -> new IllegalArgumentException("Citizen not found with id: " + birthId));

        birth.setMere_CIN(mere_CIN);
        return birthRepository.save(birth);
    }

    @Transactional
    public Birth update_Pere_CIN(Long birthId, String mere_CIN) {
        Birth birth = birthRepository.findById(birthId)
        .orElseThrow(() -> new IllegalArgumentException("Citizen not found with id: " + birthId));

        birth.setMere_CIN(mere_CIN);
        return birthRepository.save(birth);
    }


    @Transactional
    public Birth update_Adress_ParentFR(Long birthId, String adress_ParentFR) {
        Birth birth = birthRepository.findById(birthId)
        .orElseThrow(() -> new IllegalArgumentException("Citizen not found with id: " + birthId));

        birth.setAdress_ParentFR(adress_ParentFR);
        return birthRepository.save(birth);
    }


    @Transactional
    public Birth update_Adress_AarentFR(Long birthId, String adress_ParentAR) {
        Birth birth = birthRepository.findById(birthId)
        .orElseThrow(() -> new IllegalArgumentException("Citizen not found with id: " + birthId));

        birth.setAdress_ParentAR(adress_ParentAR);
        return birthRepository.save(birth);
    }



}
