package simsek.ali.VeterinaryManagementProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simsek.ali.VeterinaryManagementProject.entity.Vaccine;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {


    //List<Vaccine> findByNameAndCodeAndProtectionFinishDateGreaterThanEqual(String name, String code, LocalDate protectionStartDate);

    List<Vaccine> findByNameAndCodeAndAnimalIdAndProtectionFinishDateGreaterThanEqual(String name, String code, Long id, LocalDate protectionStartDate);

    List<Vaccine> findByAnimalId(Long id);

    List<Vaccine> findByProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate);
}
