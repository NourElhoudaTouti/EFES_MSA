package Testing.Project.CITIZEN;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

//import jakarta.transaction.Transactional;
@Transactional
@Service
public class CitizenService{
    private final CitizenRepository citizenRepository;
    
    @Autowired
    public CitizenService(CitizenRepository citizenRepository){
        this.citizenRepository=citizenRepository;
    }

/*public record CitizenService(CitizenRepository citizenRepository) { //was working
        
    private static final Logger logger = LoggerFactory.getLogger(CitizenService.class);

    public CitizenService(CitizenRepository citizenRepository) {
        this.citizenRepository = citizenRepository;
        logger.info("CitizenRepository autowired successfully: " + (this.citizenRepository != null));
    }*/

    public void RegisterCitizen(CitizenRegistrationRequest request) {
        System.out.println("wow");
        Citizen citizen = Citizen.builder()
                .NameFR(request.NameFR())
                .NameAR(request.NameAR())
                .Birth_Date(request.Birth_Date())
                .CNSS(request.CNSS())
                .CIN(request.CIN())
                .addressFR(request.addressFR())
                .addressAR(request.addressAR())
                .build();
        System.out.println("Saving citizen: " + citizen);
        citizenRepository.save(citizen);
        System.out.println("Citizen saved successfully");
    }



      public List<Citizen> getAllCitizens(){
        return citizenRepository.findAll();
    }

    public void DeleteCitizen(Long citizenId){
        boolean exists= citizenRepository.existsById(citizenId);
        if (!exists){
            throw new IllegalStateException(
                "citizen with Id"+ citizenId +"does not exist"); 
        }
        citizenRepository.deleteById(citizenId);


    }
    //SEARCH for CIN
     public boolean checkCitizenExistence(String cin) {
            return citizenRepository.existsByCIN(cin);
        }


    public Citizen getCitizenByCIN(String cin) {
        return citizenRepository.findByCIN(cin);
    }



    @Transactional
    public Citizen updateNameFR(Long citizenId, String newNameFR) {
        Citizen citizen = citizenRepository.findById(citizenId)
                .orElseThrow(() -> new IllegalArgumentException("Citizen not found with id: " + citizenId));

        citizen.setNameFR(newNameFR);
        return citizenRepository.save(citizen);
    }

    @Transactional
    public Citizen updateNameAR(Long citizenId, String NewnameAR) {
        Citizen citizen = citizenRepository.findById(citizenId)
                .orElseThrow(() -> new IllegalArgumentException("Citizen not found with id: " + citizenId));

        citizen.setNameAR(NewnameAR);
        return citizenRepository.save(citizen);
    }

    @Transactional
    public Citizen updateadressFR(Long citizenId, String NewAdressFR) {
        Citizen citizen = citizenRepository.findById(citizenId)
                .orElseThrow(() -> new IllegalArgumentException("Citizen not found with id: " + citizenId));

        citizen.setAddressFR(NewAdressFR);
        return citizenRepository.save(citizen);
    }

    @Transactional
    public Citizen updateadressAR(Long citizenId, String NewAdressAR) {
        Citizen citizen = citizenRepository.findById(citizenId)
                .orElseThrow(() -> new IllegalArgumentException("Citizen not found with id: " + citizenId));

        citizen.setAddressAR(NewAdressAR);
        return citizenRepository.save(citizen);
    }

    @Transactional
    public Citizen updateCNSS(Long citizenId, String NewCNSS) {
        Citizen citizen = citizenRepository.findById(citizenId)
                .orElseThrow(() -> new IllegalArgumentException("Citizen not found with id: " + citizenId));

        citizen.setCNSS(NewCNSS);
        return citizenRepository.save(citizen);
    }

    @Transactional
    public Citizen updateCIN(Long citizenId, String NewCIN) {
        Citizen citizen = citizenRepository.findById(citizenId)
                .orElseThrow(() -> new IllegalArgumentException("Citizen not found with id: " + citizenId));

        citizen.setCIN(NewCIN);
        return citizenRepository.save(citizen);
    }

    @Transactional
    public Citizen updateBirth_Date(Long citizenId, LocalDate NewBirth_Date) {
        Citizen citizen = citizenRepository.findById(citizenId)
                .orElseThrow(() -> new IllegalArgumentException("Citizen not found with id: " + citizenId));

        citizen.setBirth_Date(NewBirth_Date);
        return citizenRepository.save(citizen);
    }
   /* public boolean updateCitizen(Long citizenId, Citizen updatedCitizen) {
        Optional<Citizen> optionalCitizen = citizenRepository.findById(citizenId);
        if (optionalCitizen.isPresent()) {
            Citizen citizen = optionalCitizen.get();
            citizen.setNameFR(updatedCitizen.getNameFR());
            citizen.setNameAR(updatedCitizen.getNameAR());
            citizen.setBirthDate(updatedCitizen.getBirthDate());
            citizen.getCNSS(updatedCitizen.getCNSS());
            citizen.setCIN(updatedCitizen.getCIN());
            citizen.setAddressFR(updatedCitizen.getAddressFR());
            citizen.setAddressAR(updatedCitizen.getAddressAR());
            citizenRepository.save(citizen);
            return true;
        } else {
            return false;
        }
    }*/
}
