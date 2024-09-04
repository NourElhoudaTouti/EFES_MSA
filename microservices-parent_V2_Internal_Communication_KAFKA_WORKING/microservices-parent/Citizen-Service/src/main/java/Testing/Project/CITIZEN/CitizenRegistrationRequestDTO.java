package Testing.Project.CITIZEN;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
@JsonIgnoreProperties(ignoreUnknown = true)
public record CitizenRegistrationRequestDTO(
        @JsonProperty("id") Long id,
        @JsonProperty("nom_child_FR") String nomChildFR,
        @JsonProperty("nom_child_AR") String nomChildAR,
        @JsonProperty("prenom_child_FR") String prenomChildFR,
        @JsonProperty("prenom_child_AR") String prenomChildAR,
        @JsonProperty("date_de_naissance_Child") LocalDate birthDate,
        @JsonProperty("lieu_naissance") String lieuNaissance,
        @JsonProperty("mere_CIN") String mereCIN,
        @JsonProperty("pere_CIN") String pereCIN,
        @JsonProperty("adress_ParentFR") String adress_ParentFR,
        @JsonProperty("adress_ParentAR") String adress_ParentAR
) {


}
