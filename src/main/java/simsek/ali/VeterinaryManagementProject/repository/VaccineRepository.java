package simsek.ali.VeterinaryManagementProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simsek.ali.VeterinaryManagementProject.entity.Vaccine;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
}
