package Testing.Project.CITIZEN;

import java.time.LocalDate;

//import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;

//import org.hibernate.mapping.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.NoArgsConstructor;

 

@RestController
@RequestMapping(path = "api/citizen")
public record CitizenController(CitizenService citizenService) {
    @PostMapping
    public void RegisterCitizen(@RequestBody CitizenRegistrationRequest  citizenRegistrationRequest){
        System.out.println("new citizen is Registered " + citizenRegistrationRequest);  
        citizenService.RegisterCitizen(citizenRegistrationRequest); 
    }
    @GetMapping //was working
    public java.util.List<Citizen> getCitizens() {
       System.out.println("yes I was here");
        return citizenService.getAllCitizens();
    }
    @DeleteMapping(path = "{citizenId}")
    public void DeleteCitizen(@PathVariable("citizenId") Long citizenId){
        citizenService.DeleteCitizen(citizenId);
    }


    //SEARCH
    //@GetMapping("/checkExistence/{cin}")
   // public boolean checkCitizenExistence(@PathVariable String cin) {
    //    return citizenService.checkCitizenExistence(cin);
    //}

    //CHECKING
    @GetMapping("/checkExistence/{cin}")
    public ResponseEntity<Boolean> checkCitizenExistence(@PathVariable String cin) {
        boolean exists = citizenService.checkCitizenExistence(cin);
        return ResponseEntity.ok(exists);
    }

    //UPDATE

    @PutMapping(path = "{citizenId}/nameFR")
    public void updateNameFR(@PathVariable("citizenId") Long citizenId,@RequestParam(required = false) String NameFR){
        citizenService.updateNameFR( citizenId,NameFR);
    }
    @PutMapping(path = "{citizenId}/nameAR")
    public void updateNameAR(@PathVariable("citizenId") Long citizenId,@RequestParam(required = false) String NameAR){
        citizenService.updateNameAR( citizenId,NameAR);
    }
    @PutMapping(path = "{citizenId}/adressFR")
    public void updateadressFR(@PathVariable("citizenId") Long citizenId,@RequestParam(required = false) String adressFR){
        citizenService.updateadressFR( citizenId,adressFR);
    }
     @PutMapping(path = "{citizenId}/adressAR")
    public void updateadressAR(@PathVariable("citizenId") Long citizenId,@RequestParam(required = false) String adressAR){
        citizenService.updateadressAR( citizenId,adressAR);
    }
     @PutMapping(path = "{citizenId}/CNSS")
    public void updateCNSS(@PathVariable("citizenId") Long citizenId,@RequestParam(required = false) String CNSS){
        citizenService.updateCNSS( citizenId,CNSS);
    }
     @PutMapping(path = "{citizenId}/CIN")
    public void updateCIN(@PathVariable("citizenId") Long citizenId,@RequestParam(required = false) String CIN){
        citizenService.updateCIN( citizenId,CIN);
    }
     @PutMapping(path = "{citizenId}/Birth_Date")
    public void updateBirth_Date(@PathVariable("citizenId") Long citizenId,@RequestParam(required = false) LocalDate Birth_Date){
        citizenService.updateBirth_Date( citizenId,Birth_Date);
    }
    @GetMapping("/getCitizen/{cin}")
    public ResponseEntity<Citizen> getCitizenByCIN(@PathVariable String cin) {
        Citizen citizen = citizenService.getCitizenByCIN(cin);
        if (citizen != null) {
            return ResponseEntity.ok(citizen);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    /*@PutMapping(path = "{citizenId}")
    public ResponseEntity<Void> updateCitizen(@PathVariable("citizenId") Long citizenId, @RequestBody Citizen updatedCitizen) {
        boolean success = citizenService.updateCitizen(citizenId, updatedCitizen);
        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }*/

   /* @Autowired
    private final CitizenService citizenService;

     
    public CitizenController(CitizenService citizenService){ /// was working
        this.citizenService=citizenService;
    }*/

    
    /*@GetMapping //was working
    public java.util.List<Citizen> getCitizens() {
        System.out.println("yes I was here");
        return citizenService.getCitizens();
    }*/
    
    /*@PostMapping //was working
    public void RegisterNewCitizen(@RequestBody Citizen citizen){
        citizenService.addNewCitizen(citizen);
    }*/


    //@GetMapping("/citizen")
   // public java.util.List<Citizen> getAllCitizens() {
    //return citizenService.getAllCitizens();
//}


}
