package simsek.ali.VeterinaryManagementProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simsek.ali.VeterinaryManagementProject.entity.AvailableDate;

@Repository
public interface AvailableDateRepository extends JpaRepository<AvailableDate, Long> {
}
