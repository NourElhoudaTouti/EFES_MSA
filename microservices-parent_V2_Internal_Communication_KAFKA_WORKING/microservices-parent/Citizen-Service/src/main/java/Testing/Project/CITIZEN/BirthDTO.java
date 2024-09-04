package Testing.Project.CITIZEN;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class BirthDTO {
    private String Nom_child_FR;
   private String Nom_child_AR;
    private LocalDate date_de_naissance_Child;
    private String CIN;
    private String CNSS;
    private String Adress_ParentFR;
    private String Adress_ParentAR;


}



