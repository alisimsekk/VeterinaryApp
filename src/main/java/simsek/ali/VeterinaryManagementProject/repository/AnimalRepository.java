package simsek.ali.VeterinaryManagementProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simsek.ali.VeterinaryManagementProject.entity.Animal;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Optional<Animal> findByNameAndSpeciesAndGenderAndDateOfBirth(String name, String species, String gender, LocalDate dateOfBirth);

    List<Animal> findByNameContaining(String name);

    List<Animal> findByCustomerId(Long id);
}
