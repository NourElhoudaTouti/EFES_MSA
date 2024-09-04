package Testing.Project.CITIZEN;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CitizenRepository extends JpaRepository<Citizen,Long>{

    boolean existsByCIN(String cin);

   // @Query("SELECT c FROM Citizen c WHERE c.CIN=?1 ")
   // Optional<Citizen> findCitizenByCIN(String CIN);

       Citizen findByCIN(String cin);

    
}
