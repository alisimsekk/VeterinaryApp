package simsek.ali.VeterinaryManagementProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simsek.ali.VeterinaryManagementProject.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
