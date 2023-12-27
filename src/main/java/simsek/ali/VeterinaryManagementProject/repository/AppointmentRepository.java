package simsek.ali.VeterinaryManagementProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simsek.ali.VeterinaryManagementProject.entity.Appointment;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Optional<Appointment> findByDateAndDoctorIdAndAnimalId(LocalDateTime date, Long id, Long id1);
    Optional<Appointment> findByDateAndDoctorId(LocalDateTime date, Long id);

}
