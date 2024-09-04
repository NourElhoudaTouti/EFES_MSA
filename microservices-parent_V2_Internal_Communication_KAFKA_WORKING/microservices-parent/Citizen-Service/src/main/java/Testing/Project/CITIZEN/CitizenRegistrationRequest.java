package Testing.Project.CITIZEN;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public record CitizenRegistrationRequest(
        Long id,
         String NameFR,
         String NameAR,
         LocalDate Birth_Date,
         String CNSS,
            String CIN,
         String addressFR,
         String addressAR

) {

}

