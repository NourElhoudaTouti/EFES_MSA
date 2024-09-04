package Testing.Project.CITIZEN;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;

@Service
public class KafkaReceiver {
    @Autowired
    private CitizenRepository citizenRepository; // Assuming you have a repository for Citizen

    @KafkaListener(topics = "BirthTopic", groupId = "citizen-group")
    public void listenBirthRecord(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            BirthDTO birthDTO = objectMapper.readValue(message, BirthDTO.class);
            Citizen citizen = Citizen.builder()
                    .NameFR(birthDTO.getNom_child_FR())
                    .NameAR(birthDTO.getNom_child_AR())
                    .Birth_Date(birthDTO.getDate_de_naissance_Child())
                    .CIN(birthDTO.getCIN())
                    .CNSS(birthDTO.getCNSS())
                    .addressFR(birthDTO.getAdress_ParentFR())
                    .addressAR(birthDTO.getAdress_ParentAR())
                    .CIN(null) // CIN is set to null
                    .CNSS(null) // CNSS is set to null
                    .build();
            citizenRepository.save(citizen);
        } catch (Exception e) {
            // Handle deserialization exception
        }
    }
}

