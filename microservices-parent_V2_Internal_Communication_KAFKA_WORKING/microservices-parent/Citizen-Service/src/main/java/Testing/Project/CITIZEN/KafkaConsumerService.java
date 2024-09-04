package Testing.Project.CITIZEN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.kafka.annotation.KafkaListener;
import Testing.Project.CITIZEN.CitizenRegistrationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
@Service
public class KafkaConsumerService {

    private final CitizenRepository citizenRepository;

    @Autowired
    public KafkaConsumerService(CitizenRepository citizenRepository){
        this.citizenRepository=citizenRepository;
    }


    @KafkaListener(topics = "BirthTopic", groupId = "citizen-group")
    public void consume(String message) {
        // Convert message to CitizenRegistrationRequest
        CitizenRegistrationRequestDTO request = convertMessageToRequest(message);

        // Logic to add record to CitizenDB
        RegisterCitizenUsingKafka(request);

    }
    public void RegisterCitizenUsingKafka(CitizenRegistrationRequestDTO request) {
        System.out.println("We are using kafka now ");
        System.out.println("new citizen is Registered using kafka" + request);
        System.out.println("Saving citizen using kafkaaaaa: ");
        // Example of combining prenom and nom for full names
        String fullNameFR = request.prenomChildFR() + " " + request.nomChildFR();
        String fullNameAR = request.prenomChildAR() + " " + request.nomChildAR();
        Citizen citizen = Citizen.builder()
                .NameFR(fullNameFR)
                .NameAR(fullNameAR)
                .Birth_Date(request.birthDate())
                .CNSS(null) // Assuming CNSS is not provided in the birth message
                .CIN(request.pereCIN()) // Assuming that we want to use pere_CIN for CIN
                .addressFR(request.adress_ParentFR())
                .addressAR(request.adress_ParentAR())
                .build();
        System.out.println("Saving citizen: " + citizen);
        citizenRepository.save(citizen);
        System.out.println("Citizen saved successfully");
        System.out.println("I used kafka");
    }
    // Implement message conversion and citizen registration logic


    /*private CitizenRegistrationRequest convertMessageToRequest(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(message, CitizenRegistrationRequest.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert JSON message to CitizenRegistrationRequest", e);
        }
    }*/

    private CitizenRegistrationRequestDTO convertMessageToRequest(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Register JavaTimeModule
        try {
            System.out.println("we are in the kafka consumer for Deserialization"+CitizenRegistrationRequestDTO.class);
            return objectMapper.readValue(message, CitizenRegistrationRequestDTO.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert JSON message to CitizenRegistrationRequest", e);
        }
    }
}
