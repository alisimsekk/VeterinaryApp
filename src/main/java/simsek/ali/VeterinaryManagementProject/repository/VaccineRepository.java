package simsek.ali.VeterinaryManagementProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simsek.ali.VeterinaryManagementProject.entity.Vaccine;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {


    Optional<Vaccine> findByNameAndCodeProtectionStartDateAfter(String name, String code, LocalDate protectionStartDate);
}
