package simsek.ali.VeterinaryManagementProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simsek.ali.VeterinaryManagementProject.entity.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
