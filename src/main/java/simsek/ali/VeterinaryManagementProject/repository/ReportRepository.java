package simsek.ali.VeterinaryManagementProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simsek.ali.VeterinaryManagementProject.entity.Report;

import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report,Long> {
    Optional<Report> findByAppointmentId(Long appointmenId);
}
