package Testing.Project.CITIZEN;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table

@NoArgsConstructor
public class Citizen {
    @Id
    @SequenceGenerator(
        name="citizen_sequence",
        sequenceName="citizen_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.IDENTITY,
        generator="citizen_Sequence"
        )
    private Long id;
    private String NameFR;
    private String NameAR;
    private LocalDate Birth_Date;
    private String CNSS;
    private String CIN;
    private String addressFR;
    private String addressAR;

    @Builder
    public Citizen(String NameFR, String NameAR, LocalDate Birth_Date, String CNSS, String CIN, String addressFR, String addressAR) {
        this.NameFR = NameFR;
        this.NameAR = NameAR;
        this.Birth_Date = Birth_Date;
        this.CNSS = CNSS;
        this.CIN = CIN;
        this.addressFR = addressFR;
        this.addressAR = addressAR;
    }

    public void setBirthDate(LocalDate Birth_Date) {
        this.Birth_Date = Birth_Date;
    }
    public LocalDate getBirthDate() {
        return Birth_Date;
    }

/*public String getNameFR() {
        return NameFR;
    }
     public String getNameAR() {
        return NameAR;
    }
     public String getCIN() {
        return CIN;
    }
     public String getCNSS() {
        return CNSS;
    }
     public String getaddressFR() {
        return addressFR;
    }
     public String getaddressAR() {
        return addressAR;
    }
    public LocalDate getBirth_Date() {
        return Birth_Date;
    }
    public void setNameFR(String NameFR) {
        this.NameFR = NameFR;
    }

    public void setNameAR(String NameAR) {
        this.NameAR = NameAR;
    }

    public void setBirth_Date(LocalDate Birth_Date) {
        this.Birth_Date = Birth_Date;
    }

    public void setCNSS(String CNSS) {
        this.CNSS = CNSS;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public void setAddressFR(String addressFR) {
        this.addressFR = addressFR;
    }

    public void setAddressAR(String addressAR) {
        this.addressAR = addressAR;
    }*/
}
