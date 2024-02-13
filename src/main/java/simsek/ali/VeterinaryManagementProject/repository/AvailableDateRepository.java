package simsek.ali.VeterinaryManagementProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import simsek.ali.VeterinaryManagementProject.entity.AvailableDate;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Repository
public interface AvailableDateRepository extends JpaRepository<AvailableDate, Long> {

    @Query(nativeQuery = true, value = "SELECT ad1_0.id, ad1_0.available_date, ad1_0.doctor_id FROM available_date ad1_0 LEFT JOIN doctor d1_0 ON d1_0.id = ad1_0.doctor_id WHERE ad1_0.available_date = ?1 AND (ad1_0.doctor_id = ?2 OR d1_0.email =?3)")
    Optional<AvailableDate> findAvailableDateAndDoctorIdOrDoctorEmail(LocalDate availableDate, Long id, String email);


    Optional<AvailableDate> findByDoctorIdAndAvailableDate(Long id, LocalDate availableDate);
}
